package com.xiao.weather.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ApiModelProperty("id")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;


    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String creatorName;


    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updaterName;

    /**
     * 乐观锁
     */
    @ApiModelProperty("乐观锁版本")
    private Integer lockVersion = 0;

}
