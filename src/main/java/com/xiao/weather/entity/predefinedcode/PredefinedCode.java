package com.xiao.weather.entity.predefinedcode;

import com.xiao.weather.entity.AbstractEntityBase;
import com.xiao.weather.util.annotation.DbInfo;
import lombok.Data;

/**
 * 预定义编码
 *
 * @author xiao_elevener
 * @date 2018-04-22 23:10
 */
@Data
@DbInfo(tableName = "predefined_code")
public class PredefinedCode extends AbstractEntityBase {

    /**
     * 编码
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

}
