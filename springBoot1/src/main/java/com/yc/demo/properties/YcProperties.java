package com.yc.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-24 10:40
 */
@ConfigurationProperties(prefix = "yc")
public class YcProperties {
    private String pname;
    private int age;

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPname() {
        return pname;
    }

    public int getAge() {
        return age;
    }
}
