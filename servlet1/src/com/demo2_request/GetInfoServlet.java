package com.demo2_request;

import com.utils.MyTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by thinkpad on 2018/4/17.
 */
@WebServlet("/GetInfoServlet")
public class GetInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //得到URL 完整请求
        String url = request.getRequestURL().toString();
        System.out.println("url：" + url);
        //得到uri web名称+资源名称
        String uri = request.getRequestURI().toString();
        System.out.println("uri: " + uri);

        //QueryString
        //准确说是接受以get方式提交的 参数=参数值
        String queryString = request.getQueryString();
        System.out.println("queryString: " + queryString);
        //把接受到的信息通过split函数分割
        if (queryString != null) {
            String queryStrings[] = queryString.split("&");
            for (String s : queryStrings) {
                String[] name_val = s.split("=");
                System.out.println(name_val[0] + "  " + name_val[1]);
                String a = new String(name_val[0].getBytes("ISO-8859-1"), "utf-8");
                String b = new String(name_val[1].getBytes("ISO-8859-1"), "utf-8");
                System.out.println(a + "   " + b);
            }
        }


        //getRemoteAddr 请求对方的ip
        String addr = request.getRemoteAddr();
        System.out.println("addr: " + addr);

        //getRemoteHost 得到请求方的主机名
        //如果该客户机没有在dns注册，则返回ip
        String host = request.getRemoteHost();
        System.out.println("host: " + host);

        //获取客户机使用的端口
        int port = request.getRemotePort();
        int serverport = request.getLocalPort();
        //很快的刷新，客户机端口号不会变，因为使用的是长连接
        //过一段时间刷新，客户机端口号可能会变
        System.out.println("客户机的端口： "+port
                +"  服务器使用的端口： "+serverport);

        //getLocalPort  getLocalAddr  getLocalName

        String head_host = request.getHeader("Host");
        System.out.println("host: " + head_host);

        System.out.println("***********");
        //getHeaderNames 方法
        //请大家把整个http请求的消息全部获取
        //1. 得到所有头
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            //取出消息头的名字
            String headername = headers.nextElement();
            System.out.println(headername + ": " + request.getHeader(headername));
        }

    }
}
