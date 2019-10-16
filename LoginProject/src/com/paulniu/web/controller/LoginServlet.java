package com.paulniu.web.controller;

import com.paulniu.domain.User;
import com.paulniu.service.IUserService;
import com.paulniu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author:niupuyue
 * date: 2019/10/15
 * time: 22:07
 * version:
 * desc:
 **/
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");

        IUserService service = new UserServiceImpl();
        // 用户登录
        User user = service.loginUser(userName, userPwd);
        if (null == user) {
            String message = String.format("对不起，用户名或密码有误！！请重新登录！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content=2;url=%s>",
                    request.getContextPath() + "/servlet/LoginUIServlet");
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        // 登录成功
        request.getSession().setAttribute("user", user);
        String message = String.format("恭喜：%s ,登陆成功！本页将在3秒后跳到首页！！<meta http-equiv='refresh' content=3;url=%s>",
                user.getUserName(),
                request.getContextPath() + "/index.jsp");
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
