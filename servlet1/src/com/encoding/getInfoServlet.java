package com.encoding;

import com.utils.MyTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by thinkpad on 2018/4/17.
 */
@WebServlet("/getInfoServlet")
public class getInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //myform.html中的method
        //针对post方法，发送的时候设置好编码即可，针对请求体里的内容
        request.setCharacterEncoding("utf-8");
//        String u = request.getParameter("username");
        //如果是get提交，则 明确说明iso-8859-1 要转成utf-8
        String u = request.getParameter("username");
        out.println(u);
//        String username = new String(u.getBytes("iso-8859-1"), "utf-8");
//        out.println(username);
        System.out.println("u=" + u+MyTools.getNewString(u));
    }
}
