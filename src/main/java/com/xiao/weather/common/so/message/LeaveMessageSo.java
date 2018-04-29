package com.xiao.weather.common.so.message;

import com.xiao.weather.common.so.BaseSo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author xiao_elevener
 * @date 2018-04-30 0:45
 */
@Data
public class LeaveMessageSo extends BaseSo {

    private static final long serialVersionUID = 6300342990987877221L;

    @ApiModelProperty("微信openId")
    private String openId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
