package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 09:54
 */
@Configuration
@ComponentScan(basePackages = {"com.huawei", "com.mimi"})
public class AppConfig {

    @Bean
    public Random r() {
        return new Random();
    }
}
