package com.xiao.weather.config;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dozer配置类
 *
 * @author xiao_elevener
 * @date 2018-04-17 16:11
 */
@Configuration
public class DozerMapper {

    @Bean
    public DozerBeanMapperFactoryBean DozerBeanMapperFactoryBean() {
        return new DozerBeanMapperFactoryBean();
    }

}
