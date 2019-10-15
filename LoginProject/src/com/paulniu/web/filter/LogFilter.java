package com.paulniu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {
    private FilterConfig config;

    public void destroy() {

        this.config = null;
        System.out.println("paulniu:结束日志过滤");

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("paulniu:开始日志过滤之前");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 记录日志
        System.out.println("paulniu:截取到用户请求地址 = "+ request.getServletPath());
        // Filter只处理链式请求，请求依然转发到目的地址
        try {
            chain.doFilter(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("paulniu:开始日志过滤");
        this.config = config;
    }

}
