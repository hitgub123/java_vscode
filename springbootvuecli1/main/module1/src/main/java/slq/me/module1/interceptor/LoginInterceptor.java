package slq.me.module1.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.exceptions.TokenExpiredException;

import lombok.extern.slf4j.Slf4j;
import slq.me.module1.util.JWTUtils;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.warn("LogInterception.preHandle" + request.getRequestURI());
        // return true;

        return preHandleInnerWithJWT(request, response, handler);
        // return preHandleInnerWithSession(request, response, handler);

    }

    private boolean preHandleInnerWithJWT(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token=null;
        String sub=null;
        // http的header中获得token
        // token = request.getHeader(JWTUtils.USER_LOGIN_TOKEN);
        // 前端使用location.href跳转，不知道怎么设置header，改用cookie保存token
        Cookie[] cookies = request.getCookies();
        // log.error(Arrays.toString(cookies));
        for (Cookie cookie : cookies) {
            // log.error(cookie.getName() + ":" + cookie.getValue());
            if (StringUtils.equals(JWTUtils.USER_LOGIN_TOKEN, cookie.getName())) {
                token = cookie.getValue();
            }
        }
        // token不存在
        if (token == null || token.equals("")) {
            log.error("token == null,redirect to login page");
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        // 验证token
        try{
            sub = JWTUtils.validateToken(token);
            log.error("validateToken sub="+ sub);
        } catch (TokenExpiredException  e) {
            log.error("token expired,redirect to login page");
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        if (sub == null || sub.equals("")) {
            log.error("token validation failed,redirect to login page");
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        // 更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)) {
            log.error("token Need Update");
            String newToken = JWTUtils.createToken(sub);
            // response.setHeader(JWTUtils.USER_LOGIN_TOKEN, newToken);
            JWTUtils.saveToken2Cookie(response, newToken);
        }
        return true;
    }

    private boolean preHandleInnerWithSession(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String user = (String) request.getSession().getAttribute("user");
        log.warn("登录的用户是：" + user);
        log.warn("Request URL: " + request.getRequestURL() + "\nContextPath:" + request.getContextPath());
        if (StringUtils.isEmpty(user)) {
            log.warn("pass");
            return true;
        } else {
            log.warn("no pass");
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}