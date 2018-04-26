package com.xiao.weather.common.so.dealhistory;

import com.xiao.weather.common.constant.DealType;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-23 10:58
 */

@Data
public class DealHistorySo {
    /**
     * 账户id
     */
    private String accountId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 金额
     */
    private Double money;

    /**
     * 类型
     */
    private DealType dealType;

    /**
     * 描述
     */
    private String description;
}
