package com.example.favorites.web.filter;

import com.example.favorites.web.comm.Const;
import com.example.favorites.web.comm.utils.Des3EncryptionUtil;
import com.example.favorites.web.domain.User;
import com.example.favorites.web.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuiyy on 2018/1/3.
 */
public class SecurityFilter implements Filter {

    protected Logger logger = Logger.getLogger(this.getClass());
    private static Set<String> greenUrlSet = new HashSet<>();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        greenUrlSet.add("/login");
        greenUrlSet.add("/register");
        greenUrlSet.add("/index");
        greenUrlSet.add("/forgotPassWord");
        greenUrlSet.add("/newPassword");
        greenUrlSet.add("/tool");
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) srequest;
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (request.getSession().getAttribute(Const.LOGIN_SESSION_KEY) == null) {
            Cookie[] cookies = request.getCookies();
            //登录界面、静态资料 免检
            if (containsSuffix(uri) || greenUrlSet.contains(uri) || containsKey(uri)) {
                logger.debug("don't check url ," + request.getRequestURI());
                chain.doFilter(srequest, sresponse);
                return;
            } else if (cookies != null) {
                boolean flag = true;
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals(Const.LOGIN_SESSION_KEY)) {
                        if (StringUtils.isNoneBlank(cookie.getValue())) {
                            flag = false;
                        } else {
                            break;
                        }
                        String value = getUserId(cookie.getValue());
                        Long userId = 0l;
                        if (userRepository == null) {
                            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                            userRepository = (UserRepository) factory.getBean("userRepository");
                        }
                        if (StringUtils.isNoneBlank(value)) {
                            userId = Long.parseLong(value);
                        }
                        User user = userRepository.findOne(userId);
                        String html = "";
                        if (null == user) {
                            html = "<script type=\"text/javascript\">window.location.href=\"_BP_login\"</script>";
                        } else {
                            request.getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
                            String referer = this.getRef(request);
                            if (referer.indexOf("/collect?") >= 0 || referer.indexOf("/lookAround/standard/") >= 0
                                    || referer.indexOf("/lookAround/simple/") >= 0) {
                                chain.doFilter(srequest, sresponse);
                                return;
                            } else {
                                html = "<script type=\"text/javascript\">window.location.href=\"_BP_\"</script>";
                            }
                        }
                        html = html.replace("_BP_", Const.BASE_PATH);
                        sresponse.getWriter().write(html);
                    }
                    if (flag) {
                        goToPage(request, sresponse);
                    } else {
                        goToPage(request, sresponse);
                    }

                }
            }
        } else {
            chain.doFilter(srequest, sresponse);
        }
    }

    //跳转登录页面
    public void goToPage(HttpServletRequest request, ServletResponse sresponse) throws IOException {
        //跳转到登陆页面
        String referer = this.getRef(request);
        logger.debug("security filter, deney, " + request.getRequestURI());
        String html = "";
        if (referer.contains("/collect?") || referer.contains("/lookAround/standard/")
                || referer.contains("/lookAround/simple/")) {
            html = "<script type=\"text/javascript\">window.location.href=\"_BP_login\"</script>";
        } else {
            html = "<script type=\"text/javascript\">window.location.href=\"_BP_index\"</script>";
        }
        html = html.replace("_BP_", Const.BASE_PATH);
        sresponse.getWriter().write(html);
    }

    @Override
    public void destroy() {

    }

    private boolean containsSuffix(String url) {
        if (url.endsWith(".js")
                || url.endsWith(".css")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".png")
                || url.endsWith(".html")
                || url.endsWith(".eot")
                || url.endsWith(".svg")
                || url.endsWith(".ttf")
                || url.endsWith(".woff")
                || url.endsWith(".ico")
                || url.endsWith(".woff2")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean containsKey(String url) {
        if (url.contains("/media/")
                || url.contains("/login") || url.contains("/user/login")
                || url.contains("/register") || url.contains("/user/regist") || url.contains("/index")
                || url.contains("/forgotPassword") || url.contains("/user/sendForgotPasswordEmail")
                || url.contains("/newPassword") || url.contains("/user/setNewPassword")
                || (url.contains("/collector") && !url.contains("/collect/detail/"))
                || url.contains("/collect/standard/") || url.contains("/collect/simple/")
                || url.contains("/user") || url.contains("/favorites") || url.contains("/comment")
                || url.startsWith("/lookAround/standard/")
                || url.startsWith("/lookAround/simple/")
                || url.startsWith("/user/")
                || url.startsWith("/feedback")
                || url.startsWith("/standard/")
                || url.startsWith("/collect/standard/lookAround/")
                || url.startsWith("/collect/simple/lookAround/")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserId(String value) {
        try {
            String userId = Des3EncryptionUtil.decode(Const.DES3_KEY, value);
            userId = userId.substring(0, userId.indexOf(Const.PASSWORD_KEY));
            return userId;
        } catch (Exception ex) {
            logger.error("解析cookies异常：" + ex);
        }
        return null;
    }

    public String codeToString(String str) {
        String strString = str;
        try {
            byte tempB[] = strString.getBytes("ISO-8859-1");
            strString = new String(tempB);
            return strString;
        } catch (Exception e) {
            return strString;
        }
    }

    public String getRef(HttpServletRequest request) {
        String referer = "";
        String param = this.codeToString(request.getQueryString());
        if (StringUtils.isNotBlank(request.getContextPath())) {
            referer = referer + request.getContextPath();
        }
        if (StringUtils.isNotBlank(request.getServletPath())) {
            referer = referer + request.getServletPath();
        }
        if (StringUtils.isNotBlank(param)) {
            referer = referer + "?" + param;
        }
        request.getSession().setAttribute(Const.LAST_REFERER, referer);
        return referer;
    }

}
