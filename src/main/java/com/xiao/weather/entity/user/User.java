package com.xiao.weather.entity.user;

import com.xiao.weather.entity.AbstractEntityBase;
import com.xiao.weather.util.annotation.DbInfo;
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
@DbInfo(tableName="user")
public class User extends AbstractEntityBase implements Serializable{

    private static final long serialVersionUID = -6313769122312170763L;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 是否锁定
     */
    private Boolean locked = Boolean.FALSE;

    /**
     * 最后尝试登陆时间
     */
    private Date lastAttemptedLoginTime;

    /**
     * 密码错误次数
     */
    private Integer passwordErrorTimes = 0;

    /**
     * 绑定的微信号
     */
    private String openId;


}
