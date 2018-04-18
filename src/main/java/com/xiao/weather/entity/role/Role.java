package com.xiao.weather.entity.role;

import com.xiao.weather.entity.AbstractEntityBase;
import com.xiao.weather.util.annotation.DbInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限
 *
 * @author xiao_elevener
 * @date 2018-04-17 22:05
 */
@Data
@ApiModel("用户")
@DbInfo(tableName = "role")
public class Role extends AbstractEntityBase implements Serializable {
    private static final long serialVersionUID = 9002508847599698999L;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色code")
    private String roleCode;

    @ApiModelProperty("描述")
    private String description;
}
