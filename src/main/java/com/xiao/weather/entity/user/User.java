package com.xiao.weather.entity.user;

import com.xiao.weather.entity.AbstractEntityBase;
import com.xiao.weather.util.annotation.DbInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author xiao_elevener
 * @date 2018-04-16 9:33
 */
@Data
@ApiModel("用户")
@DbInfo(tableName="user")
public class User extends AbstractEntityBase implements Serializable{

    private static final long serialVersionUID = -6313769122312170763L;

    @ApiModelProperty("登陆账号")
    private String loginName;

    @ApiModelProperty("用户名")
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
    private Integer passwordErrorTimes = 0;


}
