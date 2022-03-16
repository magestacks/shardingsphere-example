package cn.acmenlt.shardingsphere.examples.jdbc5.readwrite.splitting;

import cn.acmenlt.shardingsphere.example.core.service.ExampleService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ReadwriteSplittingApplicationTests {

    @Resource
    private ExampleService readWriteSplittingUserService;

    @Test
    @SneakyThrows
    void contextLoads() {
        try {
            readWriteSplittingUserService.processSuccess();
        } finally {
            readWriteSplittingUserService.cleanEnvironment();
        }
    }
}
