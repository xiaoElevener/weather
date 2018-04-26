package com.xiao.weather.entity.dealhistory;

import com.xiao.weather.common.constant.DealType;
import com.xiao.weather.entity.AbstractEntityBase;
import com.xiao.weather.util.annotation.DbInfo;
import lombok.Data;

/**
 * 交易记录
 *
 * @author xiao_elevener
 * @date 2018-04-22 23:33
 */
@Data
@DbInfo(tableName = "deal_history")
public class DealHistory extends AbstractEntityBase {

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
