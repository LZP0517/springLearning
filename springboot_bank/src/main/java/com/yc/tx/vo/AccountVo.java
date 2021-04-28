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
public class AccountVo implements Serializable {
    private static final long serialVersionUID = 6371809472277220345L;
    private Integer accountId;
    private Double money;
    private Integer inAccountId;
}
