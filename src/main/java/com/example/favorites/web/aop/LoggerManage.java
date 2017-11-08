package com.example.favorites.web.aop;

import java.lang.annotation.*;

/**
 * Created by cuiyy on 2017/10/25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {
     String description();
}
