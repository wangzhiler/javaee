package com.service;

import com.domain.User;
import com.util.SqlHelper;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2018/4/22.
 */
public class UsersService {

//    Connection ct = null;
//    PreparedStatement ps = null;
//    ResultSet rs = null;
//    String driver="oracle.jdbc.driver.OracleDriver";
//    String url="jdbc:oracle:thin:@//localhost:1521/orcl1";
//    String dbuser = "scott";
//    String dbpassword = "tiger";

    //按照分页来获取用户
    //公司 把 resultset-> User对象-> ArrayList集合
    public ArrayList getUserByPage(int pageNow, int pageSize) {
        ArrayList al = new ArrayList<User>();
        //查询sql
        String sql="select * from " +
        "(select t.*, rownum rn from " +
                "(select * from users order by id) t " +
                "where ROWNUM<="+pageSize*pageNow+") where rn>="+(pageSize*(pageNow-1)+1);
        ResultSet rs = SqlHelper.executeQuery(sql, null);
        //二次封装
        try {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setGrade(rs.getInt(4));
                u.setPassword(rs.getString(5));
                //千万忘记 u->Arraylist
                al.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }

        return al;
    }


    //写一个验证用户是否合法的函数
    public boolean CheckUser(User user) {

        boolean b = false;

        //使用SqlHelper来完成查询任务
        String sql = "select * from users where id=? and passwd=?";
        String parameters[] = {user.getId()+"", user.getPassword()};
        ResultSet rs = SqlHelper.executeQuery(sql, parameters);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }

        /*
        //连接数据库
        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(url, dbuser, dbpassword);
            ps = ct.prepareStatement("select * from users where id=? and passwd=?");
            ps.setObject(1, user.getId());
            ps.setObject(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                b = true;
            }

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
        */
        return b;
    }
}
