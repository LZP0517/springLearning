package com.yc.demo;

import com.yc.demo.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController //@Controller控制层 @Restful 以rest规范
@EnableConfigurationProperties(YcProperties.class)
public class SpringBoot1Application extends SpringBootServletInitializer {
    private static Log log = LogFactory.getLog(SpringBoot1Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBoot1Application.class);
    }

    public static void main(String[] args) {
        log.info("启动服务器");
        SpringApplication.run(SpringBoot1Application.class, args);
    }
}

