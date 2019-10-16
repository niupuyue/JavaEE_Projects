<%--
  Created by IntelliJ IDEA.
  User: niupule
  Date: 2019/10/15
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body style="text-align: center">
<form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
    用户名:<input type="text" name="userName" placeholder="请输入用户名"><br/>
    密 码:<input type="password" name="userPwd" placeholder="请输入密码"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
