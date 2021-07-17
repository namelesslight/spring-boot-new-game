package com.example.springbootnewgame.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "GameFilter", urlPatterns = "")
@Component
public class GameFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        String URL = httpServletRequest.getRequestURL().toString();
        System.out.println("=====");
        System.out.println(uri);
        System.out.println(URL);
        if (uri.contains(".js") || uri.contains(".css") || uri.contains("ico"))
            chain.doFilter(request, response);
        if (URL.contains("Login") || URL.contains("Register")) {
            chain.doFilter(request, response);
        } else {
            Boolean flag = false;
            if (httpServletRequest.getCookies() != null) {
                for (Cookie c : httpServletRequest.getCookies()) {
                    if (c.getName().equals("user_name")) {
                        System.out.println(c.getName());
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect("/goLogin");
                return;
            }
        }
    }
}
