package com.view;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by thinkpad on 2018/4/16.
 */
@WebServlet("/LoginServlet")

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //返回一个界面（html技术）
        out.println("<img src='img/head.png'/><hr/>");

        out.println("<h1>用户登录</h1>");
        //action 应该这样写 /web应用名/Servlet的url
        out.println("<form action='/usermanager/LoginManageServlet' method='post'>");
        out.println("用户id:<input type='text' name='id'/><br/>");
        out.println("密  码:<input type='password' name='password'/><br/>");
        out.println("<input type='submit' value='登录'/><br/>");
        out.println("</form>");

        String errInfo = (String) request.getAttribute("err");
        if (errInfo != null) {
            out.println("<font color='red'>" + errInfo + "</font>");
        }

        out.println("<hr/><img src='img/end.png'/>");

    }
}
