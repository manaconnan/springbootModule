/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Map;

//@Configuration
@Slf4j
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }


    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/*.png");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        //@Override
        //public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        //        throws Exception {
        //    HttpSession session = request.getSession();
        //    if (session.getAttribute(SESSION_KEY) != null){
        //        return true;
        //    }
        //
        //    // 跳转登录
        //    String url = "/login";
        //    response.sendRedirect(url);
        //    return false;
        //}
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            //用户掉线或被挤掉，保存当前链接并重定向到登录页面
            if (request.getHeader("x-requested-with") == null) {//非ajax(异步)请求，则保存当前访问链接
                String queryUrl = request.getQueryString() == null ? "" : ("?" + request.getQueryString());//获取参数
                String requestUrl = request.getServletPath() + queryUrl;//httpRequest.getServletPath(),获取链接
                if (session.getAttribute("redirect_link") == null) {
                    session.setAttribute("redirect_link", requestUrl);
                }
            }
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }

            //多用户登录限制判断,并给出提示信息
            //boolean isLogin = false;
            //if (user != null) {
            //    Map<String, String> loginUserMap = (Map<String, String>) session.getServletContext().getAttribute("loginUserMap");
            //    String sessionId = session.getId();
            //    for (String key : loginUserMap.keySet()) {
            //        //用户已在另一处登录
            //        if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
            //            isLogin = true;
            //            break;
            //        }
            //    }
            //}
            //if (isLogin) {
            //    Map<String, String> loginOutTime = (Map<String, String>) session.getServletContext().getAttribute("loginOutTime");
            //    session.setAttribute("mess", "用户：" + user.getUserName() + "，于 " + loginOutTime.get(user.getUserName()) + " 已在别处登录!");
            //    loginOutTime.remove(user.getUserName());
            //    session.getServletContext().setAttribute("loginUserMap", loginOutTime);
            //    response.sendRedirect(request.getContextPath() + "/login");
            //    return false;
            //}

            return super.preHandle(request, response, handler);
        }

        @Override
        public void postHandle(HttpServletRequest request,
                               HttpServletResponse response, Object handler,
                               ModelAndView modelAndView) throws Exception {

            log.info("postHandle run=============");
            //super.postHandle(request, response, handler, modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response, Object handler, Exception ex)
                throws Exception {
           log.info("afterCompletion ru =======");
            //super.afterCompletion(request, response, handler, ex);
        }
    }
}