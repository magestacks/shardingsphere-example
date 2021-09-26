package cn.acmenlt.shardingsphere.examples.jdbc;

import cn.acmenlt.shardingsphere.example.core.service.ExampleService;
import cn.acmenlt.shardingsphere.examples.jdbc.base.BaseTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

class ShadowTests extends BaseTest {

    @Resource
    private ExampleService shadowUserService;

    @Test
    @SneakyThrows
    void contextLoads() {
        try {
            shadowUserService.initEnvironment();
            shadowUserService.processSuccess();
        } finally {
            shadowUserService.cleanEnvironment();
        }
    }

}
