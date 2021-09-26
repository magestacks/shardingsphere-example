package cn.acmenlt.shardingsphere.example.core.service.impl;

import cn.acmenlt.shardingsphere.example.core.entity.ShadowUser;
import cn.acmenlt.shardingsphere.example.core.repository.ShadowUserRepository;
import cn.acmenlt.shardingsphere.example.core.service.ExampleService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shadow user service impl.
 *
 * @author chen.ma
 * @date 2021/9/26 16:07
 */
@Slf4j
@Service(value = "shadowUserService")
public class ShadowUserServiceImpl implements ExampleService {

    @Resource
    private ShadowUserRepository shadowUserRepository;

    @Override
    public void initEnvironment() throws SQLException {
        shadowUserRepository.createTableIfNotExists();
        shadowUserRepository.truncateTable();
    }

    @Override
    public void cleanEnvironment() throws SQLException {
        shadowUserRepository.dropTable();
    }

    @Override
    public void processSuccess() throws SQLException {
        log.info("----------- Process success begin -----------");
        List<Long> userIds = insertData();
        deleteData(userIds);
        log.info("----------- Process success finish -----------");
    }

    @Override
    public void processFailure() throws SQLException {

    }

    @Override
    public void printData() throws SQLException {

    }

    @SneakyThrows
    private List<Long> insertData() {
        int maxNum = 10;
        List<Long> userIds = new ArrayList(maxNum);
        for (int i = 0; i < maxNum; i++) {
            ShadowUser user = new ShadowUser();
            user.setUserId(i);
            user.setUserName("shadow_user_name_" + i);
            user.setPwd("shadow_user_psd_" + i);
            user.setShadow(i % 2 == 0);
            shadowUserRepository.insert(user);
            userIds.add((long) user.getUserId());
        }

        return userIds;
    }


    @SneakyThrows
    private void deleteData(final List<Long> userIds) {
        for (Long each : userIds) {
            shadowUserRepository.delete(each);
        }
    }

}
