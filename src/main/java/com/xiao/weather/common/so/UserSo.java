package com.xiao.weather.common.so;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiao_elevener
 * @date 2018-04-17 14:12
 */
@Data
public class UserSo extends BaseSo implements Serializable{

    private static final long serialVersionUID = -9139402342877534378L;

    @ApiModelProperty("登陆账号")
    private String loginName;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("加密盐值")
    private String salt;

    @ApiModelProperty(value = "是否锁定")
    private Boolean locked = Boolean.FALSE;

    @ApiModelProperty(value = "最后尝试登陆时间")
    private Date lastAttemptedLoginTime;

    @ApiModelProperty(value = "密码错误次数")
    private Integer passwordErrorTimes;

}
