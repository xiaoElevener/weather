package com.xiao.weather.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时器切面
 *
 * @author xiao_elevener
 * @date 2018-04-17 23:15
 */
@Aspect
@Component
@Slf4j
public class TimerAspect {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 定时器切面
     */
    @Pointcut("execution(public * com.xiao.weather.timer.*.*(..))")
    public void timerLog() {
    }


    @Around("timerLog()")
    public Object surround(ProceedingJoinPoint pjp) throws ParseException {
        String clazzName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Date startTime = format.parse(format.format(System.currentTimeMillis()));
        log.info("定时任务开始:" + clazzName + "." + methodName + "   " + startTime);

        try {
            Object o =  pjp.proceed();
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }finally {
            Date endTime = format.parse(format.format(System.currentTimeMillis()));
            log.info("定时任务结束:" + clazzName + "." + methodName + "   " + endTime);
        }

    }
}
