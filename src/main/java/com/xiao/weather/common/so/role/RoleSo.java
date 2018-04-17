package com.xiao.weather.common.so.role;

import com.xiao.weather.common.so.BaseSo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author xiao_elevener
 * @date 2018-04-17 22:40
 */
public class RoleSo extends BaseSo implements Serializable {
    private static final long serialVersionUID = -6393852533893814420L;


    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("描述")
    private String description;
}
