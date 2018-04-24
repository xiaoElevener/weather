package com.xiao.weather.common.vo.dealhistory;

import com.xiao.weather.common.constant.DealType;
import com.xiao.weather.common.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-23 10:52
 */
@ApiModel("交易记录")
@Data
public class DealHistoryVo extends AbstractVo{

    @ApiModelProperty("账户id")
    private String accountId;


    @ApiModelProperty("用户id")
    private String userId;


    @ApiModelProperty("金额")
    private double money;


    @ApiModelProperty("类型")
    private DealType dealType;


    @ApiModelProperty("描述")
    private String description;

}
