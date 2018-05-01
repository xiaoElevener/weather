package com.xiao.weather.common.vo.dealhistory;


import com.xiao.weather.common.constant.DealType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 每日统计
 *
 * @author xiao_elevener
 * @date 2018-04-26 19:50
 */
@Data
@ApiModel(value = "DailyStatisticalVo", description = "每日统计数据")
public class DailyStatisticalVo implements Serializable {

    private static final long serialVersionUID = 8252567325046078406L;

    @ApiModelProperty("交易类型")
    private DealType dealType;

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("总金额")
    private Double sum;

}
