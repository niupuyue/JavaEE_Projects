package com.paulniu.dao.impl;

import com.paulniu.dao.IUserDao;
import com.paulniu.domain.User;
import com.paulniu.util.XmlUtils;
import com.sun.javaws.jnl.XMLUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.sql.*;
import java.text.SimpleDateFormat;

public class UserDaoImpl implements IUserDao {

    //JDBC驱动名以及数据库URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/javawebdb";
    // 数据库的用户名和密码
    static final String USER = "root";
    static final String PASS = "root";

    @Override
    public User find(String userName, String userPwd) {
        User user = new User();
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行 SQL 查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, userName, userPwd,email,birthday FROM users WHERE userName = " + userName + " AND userPwd = " + userPwd;
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String id = String.valueOf(rs.getInt("id"));
                user.setId(id);
                String userName1 = rs.getString("userName");
                user.setUserName(userName1);
                String userPwd1 = rs.getString("userPwd");
                user.setUserPwd(userPwd1);
                String email = rs.getString("email");
                user.setEmail(email);
                String date = rs.getString("birthday");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(sdf.parse(date));
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 最后是用于关闭资源的块
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public int add(User user) {
        if (user == null) {
            return 0;
        }
        Connection conn = null;
        PreparedStatement stmt;
        try {
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");
            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO users VALUES(?,?,?,?)";
            //实例化 PreparedStatement
            stmt = conn.prepareStatement(sql);
            //传入参数，这里的参数来自于一个带有表单的jsp文件，很容易实现
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPwd());
            stmt.setString(3, user.getEmail());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stmt.setString(4, sdf.format(user.getBirthday()));
            //执行数据库更新操作，不需要SQL语句
            int count = stmt.executeUpdate();
            return count;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public User find(String userName) {
        return null;
    }
}
