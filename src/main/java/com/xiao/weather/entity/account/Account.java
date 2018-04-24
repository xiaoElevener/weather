package com.xiao.weather.entity.account;

import com.xiao.weather.entity.AbstractEntityBase;
import lombok.Data;

/**
 * 账户实体类
 *
 * @author xiao_elevener
 * @date 2018-04-22 23:25
 */
@Data
public class Account extends AbstractEntityBase {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 余额
     */
    private double balance;


}
