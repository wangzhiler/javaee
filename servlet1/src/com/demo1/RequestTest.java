package com.demo1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

/**
 * Created by thinkpad on 2018/4/16.
 */
@WebServlet("/RequestTest")
public class RequestTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        //获取用户浏览器Referer
        String referer = request.getHeader("Referer");
        out.println("referer: "+referer);

        if (referer == null || !referer.startsWith("http://localhost:8080/servlet1")) {
            response.sendRedirect("/servlet1/Error");
            return;
        }
        out.println("referer= " + referer);

        //通过request对象来获取http请求信息
        //取出host
        String host = request.getHeader("Host");
        out.println("host= " + host);

        out.println("这是我们非常重要的信息..1234");
    }
}