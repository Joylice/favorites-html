package com.example.favorites.web.controller;

import com.example.favorites.web.comm.Const;
import com.example.favorites.web.comm.utils.Des3EncryptionUtil;
import com.example.favorites.web.comm.utils.MD5Util;
import com.example.favorites.web.domain.User;
import com.example.favorites.web.domain.result.ExceptionMsg;
import com.example.favorites.web.domain.result.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by cuiyy on 2017/10/26.
 */
@Controller
public class BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }

    protected Response result() {
        return new Response();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected User getUser() {
        return (User) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }

    protected String getUserId() {
        String id = "";
        User user = getUser();
        if (user != null) {
            id = user.getId();
        }
        return id;
    }

    protected String getUserName() {
        String userName = "云收藏";
        User user = getUser();
        if (user != null) {
            userName = user.getUserName();
        }
        return userName;
    }

    protected String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }

    protected String getPwd(String password) {
        try {
            String pwd = MD5Util.encrypt(password + Const.PASSWORD_KEY);
            return pwd;
        } catch (Exception e) {
            logger.error("密码加密异常：", e);
        }
        return null;
    }

    protected String cookieSign(String value) {
        try {
            value = value + Const.PASSWORD_KEY;
            String sign = Des3EncryptionUtil.encode(Const.DES3_KEY, value);
            return sign;
        } catch (Exception e) {
            logger.error("cookie签名异常：", e);
        }
        return null;
    }
}
