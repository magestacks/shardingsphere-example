package cn.acmenlt.shardingsphere.example.core.service.impl;

import cn.acmenlt.shardingsphere.example.core.entity.UserInfo;
import cn.acmenlt.shardingsphere.example.core.repository.ReadWriteSplittingUserRepository;
import cn.acmenlt.shardingsphere.example.core.service.ExampleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * readWriteSplittingUserService.
 *
 * @author chen.ma
 * @date 2022/3/16 21:46
 */
@Slf4j
@AllArgsConstructor
@Service(value = "readWriteSplittingUserService")
public class ReadWriteSplittingUserServiceImpl implements ExampleService {

    private final ReadWriteSplittingUserRepository readWriteSplittingUserRepository;

    @Override
    public void initEnvironment() throws SQLException {
        readWriteSplittingUserRepository.createTableIfNotExists();
        readWriteSplittingUserRepository.truncateTable();
    }

    @Override
    public void cleanEnvironment() throws SQLException {

    }

    @Override
    public void processSuccess() throws SQLException {
        UserInfo user = new UserInfo();
        for (int i = 0; i < 10; i++) {
            user.setUserName("shadow_user_name_" + i);
            user.setPwd("shadow_user_psd_" + i);
            readWriteSplittingUserRepository.insert(user);
        }

        for (int i = 0; i < 10; i++) {
            List<UserInfo> userInfos = readWriteSplittingUserRepository.selectAll();
            System.out.println(userInfos);
        }
    }

    @Override
    public void processFailure() throws SQLException {

    }

    @Override
    public void printData() throws SQLException {

    }

}
