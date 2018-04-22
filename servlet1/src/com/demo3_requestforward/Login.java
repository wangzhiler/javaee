package com.demo3_requestforward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by thinkpad on 2018/4/17.
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        out.println("<h1>用户登录</h1>");
        //action 应该这样写 /web应用名/Servlet的url
        out.println("<form action='/servlet1/Servlet1' method='post'>");
        out.println("用户名:<input type='text' name='username'/><br/>");
        out.println("密  码:<input type='password' name='password'/><br/>");
        out.println("<input type='submit' value='登录'/><br/>");
        out.println("</form>");

    }
}
