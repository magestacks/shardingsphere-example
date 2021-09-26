package cn.acmenlt.shardingsphere.examples.jdbc4;

import cn.acmenlt.shardingsphere.example.core.service.ExampleService;
import cn.acmenlt.shardingsphere.examples.jdbc4.base.BaseTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * Shadow tests.
 *
 * @author chen.ma
 * @date 2021/9/26 21:47
 */
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
