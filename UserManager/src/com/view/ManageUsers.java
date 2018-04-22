package com.view;

import com.domain.User;
import com.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2018/4/18.
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript' language='javascript'>");
        out.println("function gotoPageNow(){" +
                "var pageNow=document.getElementById('pageNow'); " +
//                "window.alert('pageNow='+pageNow.value);}");
                "window.open('/usermanager/ManageUsers?pageNow='+pageNow.value, '_self'); }");
        out.println("</script>");
        out.println("<img src='img/head.png'/>欢迎"
                +"登陆<a href='/usermanager/MainFrame'>返回主界面</a>" +
                "<a href='/usermanager/LoginServlet''>安全退出</a><hr/>");

        out.println("<h1>管理用户</h1>");

        //从数据库中取出用户信息，并显示
        String driver="oracle.jdbc.driver.OracleDriver";
        String url="jdbc:oracle:thin:@//localhost:1521/orcl1";
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        //定义分页需要的变量
        int pageNow = 1; //当前页
        //接受用户的pageNow
        String sPageNow = request.getParameter("pageNow");
        if (sPageNow != null) {
            pageNow = Integer.parseInt(sPageNow);
        }
        int pageSize = 3;  //指定每页显示3条记录
        int pageCount = 1;  //该值是计算出来
        int rowCount = 1;  //表示共有多少记录，从数据库查询的




        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(url, "scott", "tiger");

            //算出共有多少页
            ps = ct.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next(); //把右边下移一步
            rowCount = rs.getInt(1);

            pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : (rowCount / pageSize + 1);

            UsersService usersService = new UsersService();
            ArrayList<User> al = usersService.getUserByPage(pageNow, pageSize);

/*
            //如果给出的条件：pageNow=2  pageSize=3
            ps = ct.prepareStatement("select * from " +
                    "(select t.*, rownum rn from " +
                    "(select * from users order by id) t " +
                    "where ROWNUM<="+pageSize*pageNow+") where rn>="+(pageSize*(pageNow-1)+1));
            rs = ps.executeQuery();
*/

            out.println("<table border=1 width=500px>");
            out.println("<tr><th>id</th><th>用户名</th><th>email</th><th>级别</th></tr>");

            for (User u : al) {
                out.println("<tr><td>"+u.getId()
                        +"</td><td>"+u.getName()
                        +"</td><td>"+u.getEmail()
                        +"</td><td>"+u.getGrade()
                        +"</td></tr>");
            }

/*
            while (rs.next()) {
                out.println("<tr><td>"+rs.getInt(1)
                        +"</td><td>"+rs.getString(2)
                        +"</td><td>"+rs.getString(3)
                        +"</td><td>"+rs.getInt(4)
                        +"</td></tr>");
            }
*/

            out.println("</table><br/>");

            if (pageNow != 1) {
                //显示上一页
                out.println("<a href='/usermanager/ManageUsers?pageNow="+(pageNow-1)+"'>上一页</a>");
            }
            //显示分页
            for(int i=1; i<=pageCount; i++) {
                out.println("<a href='/usermanager/ManageUsers?pageNow="+i+"'><"+i+"></a>");
            }
            if (pageNow != pageCount) {
                out.println("<a href='/usermanager/ManageUsers?pageNow="+(pageNow+1)+"'>下一页</a>");
            }

            //显示分页信息
            out.println("&nbsp;&nbsp;&nbsp;当前页"+pageNow+"/总页数"+pageCount+"<br/>");
            out.println("跳转到<input type='text' id='pageNow' name='pageNow'/> <input type='button' onclick='gotoPageNow()' value='跳'/>");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        out.println("<hr/><img src='img/end.png'/>");

    }
}
