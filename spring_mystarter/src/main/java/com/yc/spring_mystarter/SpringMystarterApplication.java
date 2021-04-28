package com.yc.spring_mystarter;

import com.yc.starter.mystarter.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringMystarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMystarterApplication.class, args);
    }

    @Autowired
    private IHelloService iHelloService;

    @GetMapping("/hello")
    public String hello() {
        return iHelloService.say();

    }
}
