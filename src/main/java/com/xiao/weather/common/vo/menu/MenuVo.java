package com.xiao.weather.common.vo.menu;

import com.xiao.weather.common.vo.AbstractVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-05-02 15:19
 */
@Data
@ApiModel(value = "MenuVo", description = "菜单")
public class MenuVo extends AbstractVo {

    private static final long serialVersionUID = -570501150594699299L;
    /**
     * 角儿id
     */
    private Long roleId;

    /**
     * 路径
     */
    private String path;
}
