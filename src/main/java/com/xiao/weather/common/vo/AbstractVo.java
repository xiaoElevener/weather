package com.xiao.weather.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础VO提供创建更新人，时间，域，返回值
 */
@Data
public class AbstractVo implements Serializable {

    private static final long serialVersionUID = 7507237342343039394L;

    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    private Date updatedTime;


    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private String creatorName;


    /**
     * 更新人
     */
    @ApiModelProperty(hidden = true)
    private String updaterName;

    /**
     * 乐观锁
     */
    private int lockVersion = 0;

}
