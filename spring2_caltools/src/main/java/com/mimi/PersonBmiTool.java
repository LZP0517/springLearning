package com.mimi;

import com.huawei.Measurable;
import org.springframework.stereotype.Component;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 09:43
 */
@Component
public class PersonBmiTool implements Measurable {
    
    @Override
    public void send(String name) {
        System.out.println(name);
    }
}
