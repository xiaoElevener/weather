package com.xiao.weather.common.vo.systemStatistical;

import com.xiao.weather.common.vo.AbstractVo;
import com.xiao.weather.common.vo.user.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户统计，微信留言统计，交易次数统计
 *
 * @author xiao_elevener
 * @date 2018-05-03 0:29
 */
@Data
@ApiModel(value = "SystemStatisticalVo", description = "系统统计数据")
public class SystemStatisticalVo implements Serializable {

    private static final long serialVersionUID = 8780754299704426443L;
    @ApiModelProperty("微信绑定用户数")
    private Integer wechatUser;

    @ApiModelProperty("今日微信留言数")
    private Integer wechatMessage;

    @ApiModelProperty("今日交易数")
    private Integer dealCount;


}
