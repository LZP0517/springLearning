package com.yc.dao;

import org.springframework.stereotype.Repository;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-04 14:42
 */
@Repository
public interface StudentDao {
    public int add(String name);

    public void update(String name);

    public String find(String name);
}
