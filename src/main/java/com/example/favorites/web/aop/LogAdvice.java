package com.example.favorites.web.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

/**
 * Created by cuiyy on 2017/10/25.
 */
@Aspect
@Service
public class LogAdvice {
    private Logger logger = Logger.getLogger(this.getClass());

    @Before("within(com.example.favorites..*)&&@annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        logger.info("执行" + loggerManage.description() + "开始");
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParam(joinPoint.getArgs()));
    }

    @AfterReturning("within(com.example.favorites..*)&&@annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        logger.info("执行" + loggerManage.description() + "结束");
    }

    @AfterThrowing(pointcut = "within(com.example.favorites..*)&&@annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        logger.error("执行" + loggerManage.description() + "异常", ex);
    }


    private String parseParam(Object[] args) {
        if (args.length < 0 & args == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("传入参数{[]}");
        for (Object o : args) {
            stringBuilder.append(ToStringBuilder.reflectionToString(o) + "     ");
        }
        return stringBuilder.toString();
    }
}
