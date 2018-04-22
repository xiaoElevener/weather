package com.xiao.weather.config;

import com.xiao.weather.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 *
 * @author xiao_elevener
 * @date 2018-04-21 16:46
 */
@Configuration
public class FilterConfig {

    @Bean(name = "LogFilter")
    public FilterRegistrationBean<LogFilter> registrationLogFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LogFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
