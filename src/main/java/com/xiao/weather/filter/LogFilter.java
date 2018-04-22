package com.xiao.weather.filter;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志过滤器
 *
 * @author xiao_elevener
 * @date 2018-04-21 17:05
 */
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("logFilter 初始化!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        log.info("startTime:{}", startTime);
        chain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        log.info("endTime:{}", endTime);
        log.info("处理时间:{}ms", (endTime - startTime));
    }

    @Override
    public void destroy() {

    }
}
