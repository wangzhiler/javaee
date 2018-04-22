package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * Created by thinkpad on 2018/4/18.
 */
@WebServlet("/cookieServlet")
public class cookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();


        //先获取cookie
        //假设我们保存上次登陆时间的cookie "lasttime" "2000-01-01 00:00:00"
        Cookie[] cookies = request.getCookies();
        boolean b = false; //假设没有lasttime cookie

        if (cookies != null) {
            //保证有cookie传来，才去遍历
            for (Cookie cookie : cookies) {
                //取出名
                String name = cookie.getName();
                if ("lasttime".equals(name)) {
                    //显示

                    out.println("您上次登录时间是" + cookie.getValue());
                    //更新
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                    String nowtime = simpleDateFormat.format(new java.util.Date());
                    Cookie mycookie = new Cookie("lasttime", nowtime);
                    //保存一周
                    mycookie.setMaxAge(7 * 3600 * 24);
                    response.addCookie(mycookie);

                    b = true;
                    break;
                }
            }
        }


        if (!b) {
            b = true;
            //没有找到
            out.println("您是第一次登录");
            //把当前日期保存到cookie
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            String nowtime = simpleDateFormat.format(new java.util.Date());
            Cookie cookie = new Cookie("lasttime", nowtime);
            //保存一周
            cookie.setMaxAge(7 * 3600 * 24);
            response.addCookie(cookie);
        }

    }
}
