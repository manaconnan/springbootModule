/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.web;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alipay.mazexiang.service.User;
import com.alipay.mazexiang.service.WebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author mazexiang
 * @version $Id: loginController.java, v 0.1 2018年09月14日 14:00 mazexiang Exp $
 */
@Controller
@Slf4j
public class loginController {

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        log.info("/=============");
        model.addAttribute("name", account);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login==========");
        return "login";
    }

    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginPost(String username, String password, HttpSession session,
                                                       HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();


        if (!"123456".equals(password)) {
            log.info("password error");
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }
        log.info("password right");
        // 设置session
        User user = new User(username,password);
        session.setAttribute(WebSecurityConfig.SESSION_KEY, user);

        Map<String, String> loginUserMap = (Map<String, String>) session.getServletContext().getAttribute("loginUserMap");

        if (loginUserMap == null){
            loginUserMap = new HashMap<>();
        }

        loginUserMap.put(username,session.getId());

        session.getServletContext().setAttribute("loginUserMap",loginUserMap);

        map.put("success", true);
        map.put("message", "登录成功");
        String requestUrl =(String) session.getAttribute("redirect_link");

        try {
            response.sendRedirect(request.getContextPath() + requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

}