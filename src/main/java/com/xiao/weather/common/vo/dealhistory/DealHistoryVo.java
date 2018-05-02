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
public class DealHistoryVo extends AbstractVo {

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户账号")
    private String loginName;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("金额")
    private Double money;

    @ApiModelProperty("类型")
    private DealType dealType;

    @ApiModelProperty("描述")
    private String description;

}
