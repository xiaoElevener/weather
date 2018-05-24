package com.xiao.weather.common.excel.convert;

import com.wuwenze.poi.convert.ExportConvert;
import com.xiao.weather.common.constant.DealType;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易类型
 *
 * @author xiao_elevener
 * @date 2018-05-13 0:25
 */
public class DealTypeConvert implements ExportConvert {

    static Map<String, String> records;

    static {
        records = new HashMap<>();
        records.put(DealType.CONSUME.toString(), "消费");
        records.put(DealType.RECHARGE.toString(), "充值");
    }

    @Override
    public String handler(Object val) {
        return val == null ? "" : records.get(val.toString());
    }
}
