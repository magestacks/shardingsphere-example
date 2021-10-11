package com.acmenlt.shardingsphere.examples.jdbc4.shadow;

import cn.acmenlt.shardingsphere.example.core.service.ExampleService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Shadow tests.
 *
 * @author chen.ma
 * @date 2021/9/26 21:47
 */
@SpringBootTest
class ShadowTests {

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
