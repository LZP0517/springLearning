package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-24 20:42
 */
@Data
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 1928355945538829384L;
    private Integer code;
    private T data;
    private String msg;
}
