package com.acmenlt.shardingsphere.examples.beforecheck;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.db.DbUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class BeforeCheckApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BeforeCheckApplication.class, args);
    }
    
    @Value("${spring.shardingsphere.datasource.ds.jdbc-url}")
    private String jdbcUrl;
    @Value("${spring.shardingsphere.datasource.ds.username}")
    private String username;
    @Value("${spring.shardingsphere.datasource.ds.password}")
    private String password;
    @Value("${spring.shardingsphere.datasource.ds.driver-class-name}")
    private String driverClassName;
    private final DataSource shardingSphereDataSource;
    
    @SneakyThrows
    @PostConstruct
    public void beforeCheckShardingSphereKeyword() {
        List<String> allSchemaTableNames = listSchemaTableName();
        Map<String, List<String>> tableKeywords = listShardingSphereKeyword(allSchemaTableNames);
        log.info("[{}] ShardingSphere keyword :: {}", getDbName(jdbcUrl), JSON.toJSONString(tableKeywords));
    }
    
    @SneakyThrows
    private Map<String, List<String>> listShardingSphereKeyword(List<String> tableNames) {
        Connection con = null;
        Statement state = null;
        ResultSet resultSet = null;
        Map<String, List<String>> tableKeys = Maps.newHashMap();
        try {
            con = shardingSphereDataSource.getConnection();
            state = con.createStatement();
            String select = "SELECT %s FROM %s;";
            for (String each : tableNames) {
                Table tableMeta = MetaUtil.getTableMeta(shardingSphereDataSource, each);
                Collection<Column> columns = tableMeta.getColumns();
                List<String> keys = Lists.newArrayList();
                for (String item : columns.stream().map(Column::getName).collect(Collectors.toList())) {
                    String checkSql = String.format(select, item, each);
                    try {
                        resultSet = state.executeQuery(checkSql);
                    } catch (Exception ex) {
                        keys.add(item);
                    }
                }
                if (CollUtil.isNotEmpty(keys)) {
                    tableKeys.put(each, keys);
                }
            }
        } finally {
            DbUtil.close(state, con, resultSet);
        }
        return tableKeys;
    }
    
    /**
     * ShardingSphere 5.1.0 does not support INFORMATION_SCHEMA.
     *
     * @return
     */
    @SneakyThrows
    private List<String> listSchemaTableName() {
        Connection con = null;
        Statement state = null;
        ResultSet result = null;
        List<String> tableNames = Lists.newArrayList();
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(jdbcUrl, username, password);
            state = con.createStatement();
            String dbName = StrBuilder.create("'").append(getDbName(jdbcUrl)).append("'").toString();
            String stuQuerySqlStr = "SELECT `TABLE_NAME` FROM `INFORMATION_SCHEMA`.`TABLES` WHERE `TABLE_SCHEMA` = " + dbName + ";";
            result = state.executeQuery(stuQuerySqlStr);
            while (result.next()) {
                tableNames.add(result.getString("TABLE_NAME"));
            }
        } finally {
            DbUtil.close(result, state, con);
        }
        return tableNames;
    }
    
    private String getDbName(String link) {
        Assert.isTrue(!StringUtils.isEmpty(link), "Database connection is empty.");
        String[] split = link.split("/");
        Assert.isTrue(split != null, String.format("The database connection format is incorrect, connection :: %s", link));
        String dbNameStr = split[3];
        int idx = dbNameStr.indexOf("?");
        return dbNameStr.substring(0, idx);
    }
}
