package com.controller;

import com.domain.User;
import com.service.UsersService;
import com.test.MyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 * Created by thinkpad on 2018/4/16.
 */
@WebServlet("/LoginManageServlet")
public class LoginManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        //接受用户提交的用户名和密码
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setPassword(password);


        //因为Servlet本身就是一个java类，因此Servlet操作数据库和普通类一样

        //创建UserSevice对象，完成到数据库的验证
        UsersService usersService = new UsersService();
        if (usersService.CheckUser(user)) {
            //说明该用户合法
            request.getRequestDispatcher("/MainFrame")
                    .forward(request, response);
        } else {
            request.setAttribute("err","用户名id或者密码有误");
            request.getRequestDispatcher("/LoginServlet")
                    .forward(request, response);
        }

        /*
        //到数据库验证
        //1.加载驱动 2.得到连接 3.创建PreparedStatement 4.执行操作 5.根据结果做处理
        String driver="oracle.jdbc.driver.OracleDriver";
        String url="jdbc:oracle:thin:@//localhost:1521/orcl1";
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(url, "scott", "tiger");
            ps = ct.prepareStatement("SELECT * FROM  users WHERE id=? and passwd=?");
            //给问号赋值
            ps.setObject(1, id);
            ps.setObject(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
                //说明该用户合法
                request.getRequestDispatcher("/MainFrame")
                        .forward(request, response);
            } else {
                request.setAttribute("err","用户名id或者密码有误");
                request.getRequestDispatcher("/LoginServlet")
                        .forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("err","您的id不是数字");
            request.getRequestDispatcher("/LoginServlet")
                    .forward(request, response);
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        */

        //简单验证
        /*
        if ( password.equals("abc")) {
            //跳转到下一个页面[servlet提供了两种Sendredirect转向forward转发]
            // sendRedirect的url： /web应用名/servlet url
//            MyData.name = username;

            //使用Session来传递数据给下个页面
            request.getSession().setAttribute("loginuser", username);
            //session可以传递对象
            User a = new User(username, password);
            request.getSession().setAttribute("aa", a);

            //只能传字符串，不能传对象，如User
            response.sendRedirect("/usermanager/MainFrame?uname="+username+"&pwd="+password);
        } else {
            //跳回
            response.sendRedirect("/usermanager/LoginServlet");
        }
        */


    }
}
