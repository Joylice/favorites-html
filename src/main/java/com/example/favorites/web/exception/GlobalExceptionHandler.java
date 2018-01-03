package com.example.favorites.web.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cuiyy on 2018/1/3.
 */
//使用该注解，不用任何的配置，
//只要把这个类放在项目中，Spring能扫描到的地方，
//就可以实现全局异常的回调。
@ControllerAdvice
public class GlobalExceptionHandler {
    protected Logger logger = Logger.getLogger(this.getClass());

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request) {
        logger.info("请求地址" + request.getRequestURL());
        ModelAndView modelAndView = new ModelAndView();
        logger.error("异常信息：" + e);
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);

        return modelAndView;
    }
}
