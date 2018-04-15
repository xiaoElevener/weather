package com.xiao.weather.util.annotation;

import java.lang.annotation.*;

/**
 * 在BO上配置数据库的相关信息，方便代码自动生成工具使用。
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DbInfo {

    String tableName();

    String seqName();
}
