package com.xiao.weather.entity.menu;

import com.xiao.weather.entity.AbstractEntityBase;
import lombok.Data;

/**
 * 角色菜单权限
 *
 * @author xiao_elevener
 * @date 2018-05-02 15:16
 */
@Data
public class Menu extends AbstractEntityBase {

    private static final long serialVersionUID = 6520026993787492636L;

    /**
     * 角儿id
     */
    private Long roleId;

    /**
     * 路径
     */
    private String path;

}
