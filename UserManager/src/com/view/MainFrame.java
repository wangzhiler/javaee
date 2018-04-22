package com.view;

import com.domain.User;
import com.test.MyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by thinkpad on 2018/4/16.
 */
@WebServlet("/MainFrame")
public class MainFrame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //取出传来的字符串
        String id = request.getParameter("id");
//        String password = request.getParameter("pwd");
//        String uname = (String)request.getSession().getAttribute("loginuser");
//        User user = (User) request.getSession().getAttribute("aa");
        out.println("<img src='img/head.png'/>欢迎"+id+"登陆<a href='/usermanager/LoginServlet'>返回重新登录</a><hr/>");

//        out.println("<h1>欢迎登陆</h1>");
//        out.println("");

        out.println("<h3>请选择您要进行的操作</h3>");
        out.println("<a href='/usermanager/ManageUsers'>管理用户</a>");
        out.println("<a href=''>添加用户</a>");
        out.println("<a href=''>查找用户</a>");
        out.println("<a href=''>退出系统</a>");

        out.println("<hr/><img src='img/end.png'/>");

    }
}
