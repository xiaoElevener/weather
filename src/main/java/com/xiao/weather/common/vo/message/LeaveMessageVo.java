package com.xiao.weather.common.vo.message;

import com.xiao.weather.common.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信留言
 *
 * @author xiao_elevener
 * @date 2018-04-28 21:59
 */
@Data
@ApiModel(value = "LeaveMessageVo", description = "微信留言")
public class LeaveMessageVo extends AbstractVo {

    private static final long serialVersionUID = -7342786876584620751L;

    @ApiModelProperty("微信openId")
    private String openId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户名")
    private String userName;
}
