package com.acmenlt.shardingsphere.examples.jdbc4.shadow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.acmenlt.shardingsphere.example.core")
@MapperScan("cn.acmenlt.shardingsphere.example.core.repository")
public class Jdbc4ShadowApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jdbc4ShadowApplication.class, args);
    }

}
