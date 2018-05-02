package com.xiao.weather.common.so.menu;

import com.xiao.weather.common.so.BaseSo;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-05-02 15:50
 */
@Data
public class MenuSo extends BaseSo {

    private static final long serialVersionUID = -4081708040153511390L;
    /**
     * 角儿id
     */
    private Long roleId;

    /**
     * 路径
     */
    private String path;

}
