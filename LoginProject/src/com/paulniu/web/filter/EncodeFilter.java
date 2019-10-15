package com.paulniu.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

public class EncodeFilter implements Filter {

    private String encoding;
    private HashMap<String,String> params = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("paulniu:开始编码过滤");
        encoding = filterConfig.getInitParameter("encoding");
        for (Enumeration<?> e = filterConfig.getInitParameterNames();e.hasMoreElements();){
            String name = (String) e.nextElement();
            String value = filterConfig.getInitParameter(name);
            params.put(name,value);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("--------------------------------------------------------");
        System.out.println("paulniu:编码过滤");
        System.out.println("需要转换的编码格式 encoding = " + encoding);
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("paulniu:编码过滤");
        System.err.println("--------------------------------------------------------");
    }

    @Override
    public void destroy() {
        System.out.println("paulniu:结束编码过滤");
        this.encoding = null;
        this.params = null;
    }
}
