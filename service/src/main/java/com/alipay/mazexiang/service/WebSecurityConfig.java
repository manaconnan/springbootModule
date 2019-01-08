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
     * ��¼session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }


    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // �ų�����
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/*.png");

        // ��������
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
        //    // ��ת��¼
        //    String url = "/login";
        //    response.sendRedirect(url);
        //    return false;
        //}
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            //�û����߻򱻼��������浱ǰ���Ӳ��ض��򵽵�¼ҳ��
            if (request.getHeader("x-requested-with") == null) {//��ajax(�첽)�����򱣴浱ǰ��������
                String queryUrl = request.getQueryString() == null ? "" : ("?" + request.getQueryString());//��ȡ����
                String requestUrl = request.getServletPath() + queryUrl;//httpRequest.getServletPath(),��ȡ����
                if (session.getAttribute("redirect_link") == null) {
                    session.setAttribute("redirect_link", requestUrl);
                }
            }
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }

            //���û���¼�����ж�,��������ʾ��Ϣ
            //boolean isLogin = false;
            //if (user != null) {
            //    Map<String, String> loginUserMap = (Map<String, String>) session.getServletContext().getAttribute("loginUserMap");
            //    String sessionId = session.getId();
            //    for (String key : loginUserMap.keySet()) {
            //        //�û�������һ����¼
            //        if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
            //            isLogin = true;
            //            break;
            //        }
            //    }
            //}
            //if (isLogin) {
            //    Map<String, String> loginOutTime = (Map<String, String>) session.getServletContext().getAttribute("loginOutTime");
            //    session.setAttribute("mess", "�û���" + user.getUserName() + "���� " + loginOutTime.get(user.getUserName()) + " ���ڱ𴦵�¼!");
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