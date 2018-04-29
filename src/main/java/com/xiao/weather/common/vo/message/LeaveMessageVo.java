package com.xiao.weather.common.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 微信留言
 *
 * @author xiao_elevener
 * @date 2018-04-28 21:59
 */
@Data
@ApiModel("微信留言")
public class LeaveMessageVo {

    @ApiModelProperty("微信openId")
    private String openId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
