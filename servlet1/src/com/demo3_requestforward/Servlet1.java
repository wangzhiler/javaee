package com.demo3_requestforward;

import com.utils.MyTools;

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
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //接受用户名
        String user = request.getParameter("username");
        PrintWriter out = response.getWriter();
        System.out.println("hcaij"+user);
//        user = MyTools.getNewString(user);
        out.println("servlet1"+user);
        System.out.println(user);

        //把user放入request域对象
        request.setAttribute("username", user);

        //表示使用转发的方法，把request和response对象传给下一个Servlet
        request.getRequestDispatcher("/Servlet2")
                .forward(request, response);
    }
}
