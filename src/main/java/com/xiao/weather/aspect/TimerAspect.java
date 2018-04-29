package com.xiao.weather.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;

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
        log.info("定时任务开始:" + clazzName + "." + methodName + "   " + LocalDateTime.now());

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            log.info("定时任务结束:" + clazzName + "." + methodName + "   " + LocalDateTime.now());
        }

    }
}
