package com.xiao.weather.common.vo.user;

import com.xiao.weather.common.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiao_elevener
 * @date 2018-04-16 15:05
 */
@Data
@ApiModel("用户vo")
public class UserVo extends AbstractVo implements Serializable{

    @ApiModelProperty("登陆账号")
    private String loginName;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("密码")
    private String password;
}
