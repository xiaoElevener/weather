package com.xiao.weather.common.so;

import com.xiao.weather.entity.AbstractEntityBase;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 基础搜索类
 *
 */
@Data
public class BaseSo extends AbstractEntityBase implements Serializable {

    private static final long serialVersionUID = 4009650342175211289L;

    /**
     * 默认的页面数
     */
    public static final int DEFAULT_PAGE_SIZE = 50;

    /**
     * 当前页面数
     */
    private int pageNumber = 1;

    /**
     * 页面数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

}
