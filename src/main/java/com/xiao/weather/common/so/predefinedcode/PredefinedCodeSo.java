package com.xiao.weather.common.so.predefinedcode;

import com.xiao.weather.common.so.BaseSo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-04-30 14:54
 */
@Data
public class PredefinedCodeSo extends BaseSo {
    private static final long serialVersionUID = -8421817995526785286L;

    @ApiModelProperty("编码")
    private String code;


    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("描述")
    private String description;
}
