<%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2019/10/15
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册新用户</title>
</head>
<body style="text-align: center">
<form action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">
    <table width="60%" border="0.5">
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="userName" placeholder="请输入用户名">${formbean.errors.userName}
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="userPwd" placeholder="请输入密码">${formbean.errors.userPwd}
            </td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td>
                <input type="password" name="confirmPwd" placeholder="请再次输入密码">${formbean.errors.confirmPwd}
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" placeholder="请输入邮箱">${formbean.errors.email}
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
                <input type="date" name="birthday">${formbean.errors.birthday}
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="清空">
            </td>
            <td>
                <input type="submit" value="注册">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
