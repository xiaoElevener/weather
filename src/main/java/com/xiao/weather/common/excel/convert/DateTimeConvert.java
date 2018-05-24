package com.xiao.weather.common.excel.convert;

import com.wuwenze.poi.convert.ExportConvert;
import com.xiao.weather.util.DateUtil;

import java.util.Date;

/**
 * 时间转化
 *
 * @author xiao_elevener
 * @date 2018-05-13 0:17
 */
public class DateTimeConvert implements ExportConvert {

    @Override
    public String handler(Object val) {
        Date date = new Date(val.toString());
        return DateUtil.date2LocalDateTime(date);
    }
}
