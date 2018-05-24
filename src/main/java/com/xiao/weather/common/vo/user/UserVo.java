package com.xiao.weather.common.vo.user;

import com.xiao.weather.common.vo.AbstractVo;
import com.xiao.weather.common.vo.role.RoleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xiao_elevener
 * @date 2018-04-16 15:05
 */
@Data
@ApiModel(value = "UserVo", description = "用户vo")
public class UserVo extends AbstractVo implements Serializable {

    private static final long serialVersionUID = 4505382889661780307L;
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

    @ApiModelProperty("是否锁定")
    private Boolean locked = Boolean.FALSE;

    @ApiModelProperty("最后尝试登陆时间")
    private Date lastAttemptedLoginTime;

    @ApiModelProperty("密码错误次数")
    private Integer passwordErrorTimes;

    @ApiModelProperty("用户拥有菜单")
    private List<String> paths;

    @ApiModelProperty("用户拥有菜单")
    private List<RoleVo> roles;

    @ApiModelProperty("用户的openId")
    private String openId;

    @ApiModelProperty("余额")
    private String balance;
}
