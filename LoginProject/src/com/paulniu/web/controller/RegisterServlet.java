package com.paulniu.web.controller;

import com.paulniu.domain.User;
import com.paulniu.service.IUserService;
import com.paulniu.service.impl.UserServiceImpl;
import com.paulniu.util.WebUtils;
import com.paulniu.web.formbean.RegisterFormBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 将客户端提交的表单数据封装到RegisterFormBean对象中
        RegisterFormBean formBean = WebUtils.request2Bean(request,RegisterFormBean.class);
        // 验证用户注册填写的表单数据
        if (formBean.validate() == false){
            // 如果验证失败，将封装了用户填写的表单数据的formBean对象返回发送给register.jsp的表单中进行显示
            request.setAttribute("formbean",formBean);
            // 验证失败说明用户填写信息有误，返回register.jsp页面
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request,response);
            return;
        }
        User user = new User();
        try {
            // 注册字符串日期转换器中
            ConvertUtils.register(new DateLocaleConverter(),Date.class);
            // 将表单数据填充到javabean中
            BeanUtils.copyProperties(user,formBean);
            // 设置用户的id属性
            user.setId(WebUtils.makeId());
            IUserService service = new UserServiceImpl();
            // 调用service层提供的注册用户服务完成注册操作
            service.registerUser(user);
            String message = String.format("注册成功！3秒后将为您自动跳转到登录页面！！！<meta http-equiv='refresh' content=3;url=%s'>",request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }catch (Exception ex){
            ex.printStackTrace();
            formBean.getErrors().put("userName","注册用户已经存在！！！");
            request.setAttribute("formbean",formBean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
