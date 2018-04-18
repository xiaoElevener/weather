package com.xiao.weather.common.vo.role;

import com.xiao.weather.common.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色Vo
 *
 * @author xiao_elevener
 * @date 2018-04-17 22:37
 */
@Data
@ApiModel("角色")
public class RoleVo extends AbstractVo implements Serializable {

    private static final long serialVersionUID = -1332244932304729434L;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色code")
    private String roleCode;

    @ApiModelProperty("描述")
    private String description;
}
