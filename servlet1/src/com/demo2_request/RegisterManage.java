package com.demo2_request;

import com.utils.MyTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by thinkpad on 2018/4/17.
 */
@WebServlet("/RegisterManage")
public class RegisterManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String u = request.getParameter("username");
        u = MyTools.getNewString(u);

        String p = request.getParameter("pwd");
        p = MyTools.getNewString(p);

        String s = request.getParameter("sex");
        s = MyTools.getNewString(s);

        //接受复选框的内容，使用getParameterValues
        String []h = request.getParameterValues("hobby");

        String c = request.getParameter("city");
        c = MyTools.getNewString(c);

        String intro = request.getParameter("intro");
        intro = MyTools.getNewString(intro);

        String hidden = request.getParameter("hidden1");
        hidden = MyTools.getNewString(hidden);


        out.println("用户名=" + u + "<br/>");
        out.println("密  码=" + p + "<br/>");
        out.println("性  别=" + s + "<br/>");
        out.println("你的爱好有 ");
        if (h != null) {
            for (int i = 0; i < h.length; i++) {
                h[i] = MyTools.getNewString(h[i]);
                out.println(h[i] + "  ");
            }
            out.println("<br/>");
        } else {
            out.println("你没有爱好<br/>");
        }
        out.println("所在城市：" + c + "<br/>");
        out.println("个人介绍：" + intro + "<br/>");
        out.println("hidden的：" + hidden);
    }
}
