package com.xiao.weather.common.excel.bean;

import com.wuwenze.poi.annotation.ExportConfig;
import com.xiao.weather.common.constant.DealType;
import lombok.Data;

import java.util.Date;

/**
 * 交易记录的excel类
 *
 * @author xiao_elevener
 * @date 2018-05-12 23:47
 */
@Data
public class DealHistoryExcelBean {

    @ExportConfig(value = "账户id", width = 50)
    private Long accountId;

    @ExportConfig(value = "用户id", width = 50)
    private Long userId;

    @ExportConfig(value = "用户账号", width = 100)
    private String loginName;

    @ExportConfig(value = "姓名", width = 100)
    private String userName;

    @ExportConfig(value = "金额", width = 100)
    private Double money;

    @ExportConfig(value = "类型", width = 100, convert = "c:com.xiao.weather.common.excel.convert.DealTypeConvert")
    private DealType dealType;

    @ExportConfig(value = "创建时间", width = 300, convert = "c:com.xiao.weather.common.excel.convert.DateTimeConvert")
    private Date createdTime;


}
