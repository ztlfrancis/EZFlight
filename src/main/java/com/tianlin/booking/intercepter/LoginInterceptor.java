package com.tianlin.booking.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Methods", "POST");

        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        System.out.println("prehandle");
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            response.sendRedirect(request.getContextPath() + "/login");
            System.out.println("nocookie");
            return false;
        }
        String username = null;

        for (Cookie item : cookies) {
            if ("username".equals(item.getName())) {
                username = item.getValue();
                System.out.println("matched");
                break;
            }
        }
        if (StringUtils.isEmpty("id")) {
            System.out.println("no id");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
     /*   HttpSession session = request.getSession();
        Object obj = session.getAttribute("username");
        if (null == obj) {
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(900);
            System.out.println("addsession");
        }*/
        System.out.println("letgo");
        return true;
    }
}