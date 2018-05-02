package com.xiao.weather.common.so.dealhistory;

import com.xiao.weather.common.constant.DealType;
import com.xiao.weather.common.so.BaseSo;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-23 10:58
 */

@Data
public class DealHistorySo extends BaseSo {
    private static final long serialVersionUID = -856023111596362118L;
    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 用户id
     */
    private Long userId;

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
