package cn.acmenlt.shardingsphere.example.core;

import cn.acmenlt.shardingsphere.example.core.service.ExampleService;

import java.sql.SQLException;

/**
 * Example execute template.
 *
 * @author chen.ma
 * @date 2021/10/10 19:26
 */
public class ExampleExecuteTemplate {

    public static void run(final ExampleService exampleService) throws SQLException {
        try {
            exampleService.initEnvironment();
            exampleService.processSuccess();
        } finally {
            exampleService.cleanEnvironment();
        }
    }

    public static void runFailure(final ExampleService exampleService) throws SQLException {
        try {
            exampleService.initEnvironment();
            exampleService.processFailure();
        } finally {
            exampleService.cleanEnvironment();
        }
    }

}
