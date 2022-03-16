package cn.acmenlt.shardingsphere.examples.jdbc5.readwrite.splitting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.acmenlt.shardingsphere.example.core")
@MapperScan("cn.acmenlt.shardingsphere.example.core.repository")
public class JdbcReadwriteSplitting5Application {

    public static void main(String[] args) {
        SpringApplication.run(JdbcReadwriteSplitting5Application.class, args);
    }

}
