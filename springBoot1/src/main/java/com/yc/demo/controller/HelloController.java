package com.yc.demo.controller;

import com.yc.demo.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-24 10:09
 */
@RestController
public class HelloController {

    //创建日志对象 必须写当前类的反射类
    private static Log log = LogFactory.getLog(HelloController.class);

    //属性注入的三种方式

    //系统环境变量
    @Autowired
    private Environment env;

//    @Value("${yc.pname}")
//    private String pname;

    @Autowired
    private YcProperties yp;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        //log.debug("******debug******");
        log.info("*********info*******");
        log.error("**********error*******");
        log.fatal("********fatal*********");
        log.info("系统环境变量：" + env);
        log.info(env.getProperty("yc.pname"));
        // log.info(pname);
        log.info(yp.getPname() + "\t" + yp.getAge());
        return String.format("Hello %s!", name);
    }
}
