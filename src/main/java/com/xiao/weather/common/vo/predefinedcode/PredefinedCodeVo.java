package com.xiao.weather.common.vo.predefinedcode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-23 11:29
 */
@Data
@ApiModel
public class PredefinedCodeVo {


    @ApiModelProperty("编码")
    private String code;


    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("描述")
    private String description;

}
