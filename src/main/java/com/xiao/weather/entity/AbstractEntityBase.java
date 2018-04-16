package com.xiao.weather.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体抽象类
 *
 * @author xiao_elevener
 * @date 2018-04-16 9:33
 */
@Data
@ApiModel("实体抽象类")
public abstract class AbstractEntityBase implements Serializable{


    private static final long serialVersionUID = 5220785636650018846L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("创建人")
    private String creatorName;

    @ApiModelProperty("更新人")
    private String updaterName;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    private Date updatedTime;

    @ApiModelProperty("乐观锁")
    private int lockVersion = 0;

}
