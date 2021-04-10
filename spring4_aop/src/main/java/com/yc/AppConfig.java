package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-09 19:26
 */
@Configuration
@ComponentScan(value = {"com.yc.biz", "com.yc.dao", "com.yc.aspect"})
@EnableAspectJAutoProxy //启用aspectJ支持
public class AppConfig {
}
