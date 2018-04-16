package com.xiao.weather.common.exception;

/**
 * @author xiao_elevener
 */
public class ExceptionBuilder {

    public static <T extends BizException> T build(T e, ExceptionAction action) {
        e.setAction(action);
        return e;
    }
}
