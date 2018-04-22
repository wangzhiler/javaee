package com.demo2_request;

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
@WebServlet("/MyInfoForm")
public class MyInfoForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<form action='/servlet1/RegisterManage' method='post'>");
        out.println("用户名：<input type='text' name='username'/><br/>");
        out.println("密  码：<input type='password' name='pwd'/><br/>");
        out.println("性别：<input type='radio' name='sex' value='男'/>男 <input type='radio' name='sex' value='女'/>女 <br/>");
        out.println("你的爱好：<input type='checkbox' name='hobby' value='音乐'/>音乐" +
                "<input type='checkbox' name='hobby' value='体育'/>体育 " +
                "<input type='checkbox' name='hobby' value='旅游'/>旅游 <br/>");
        out.println("所在城市：<select name='city'><option value='北京'>北京</option>" +
                "<option value='重庆'>重庆</option></select><br/>");
        out.println("你的介绍<textarea cols='20' rows='10' name='intro'>..." +
                "</textarea><br/>");
        out.println("提交照片<input type='file' name='photo'/><br/>");
        //什么时候使用hidden传数据
        //1. 不希望用户看到数据
        //2. 不希望影响界面，同时要使用该数据
        out.println("<input type='hidden' value='abc' name='hidden1/><br/>");
        out.println("<input type='submit' value='提交信息'/>");
        out.println("</form>");
    }
}
