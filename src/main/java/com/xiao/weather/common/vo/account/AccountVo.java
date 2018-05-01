package com.xiao.weather.common.vo.account;

import com.xiao.weather.common.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-23 10:51
 */
@ApiModel(value = "AccountVo", description = "账户vo")
@Data
public class AccountVo extends AbstractVo{

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("余额")
    private Double balance;

    @ApiModelProperty("欠费次数")
    private Integer overdraft;
}
