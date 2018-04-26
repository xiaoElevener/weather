package com.xiao.weather.common.so.account;

import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-23 10:58
 */
@Data
public class AccountSo {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 欠费次数
     */
    private Integer overdraft;
}
