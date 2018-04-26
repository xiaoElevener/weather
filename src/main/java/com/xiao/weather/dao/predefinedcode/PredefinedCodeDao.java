package com.xiao.weather.dao.predefinedcode;

import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.predefinedcode.PredefinedCode;

/**
 * @author xiao_elevener
 */
public interface PredefinedCodeDao extends Dao<PredefinedCode> {

    /**
     * 通过code查预定义编码
     *
     * @param code
     * @return
     */
    PredefinedCode findByCode(String code);
}



