package com.xiao.weather.service.core;

import com.xiao.weather.util.dozer.DozerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public abstract class AbstractServiceImpl {

    @Autowired
    protected DozerHelper dozer;

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected DozerHelper getDozer() {
        return dozer;
    }
}