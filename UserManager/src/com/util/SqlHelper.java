package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by thinkpad on 2018/4/22.
 * 这是一个操作数据库的工具类
 */
public class SqlHelper {
    //定义需要的变量
    private static Connection ct = null;
    //在大多数情况下，我们使用PreparedStatement来替代Statement
    //这样可以防止sql注入
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static CallableStatement cs = null;

    //连接数据库参数
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String password = "";

    //读取配置文件
    private static Properties pp = null;
    private static InputStream fis = null;

    //加载驱动，只需要一次
    static{
        try {
            //从dbinfo.propertis文件中读取配置信息
            pp = new Properties();
//            fis = new FileInputStream("/dbinfo.properties"); //=>tomcat的主目录?
            //当我们使用java web的时候，读取文件要使用类加载器
            fis = SqlHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            password = pp.getProperty("password");
            Class.forName(driver);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis = null;
        }
    }

    //得到连接
    public static Connection getConnection() {
        try {
            ct = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ct;
    }

    //分页问题?
    public static ResultSet executeQuery2() {
        return null;
    }

    //调用存储过，有返回Result
    //sql call 过程 ??
    public static CallableStatement callPro2
    (String sql, String[] inparameters, Integer[] outparameters) {
        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            if (inparameters!=null) {
                for(int i=0; i<inparameters.length; i++) {
                    cs.setObject(i + 1, inparameters[i]);
                }
            }

            //给out参数赋值
            if (outparameters != null) {
                for(int i=0; i<outparameters.length;i++) {
                    cs.registerOutParameter(inparameters.length+1+i,outparameters[i]);
                }
            }
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不需要关闭
        }
        return cs;
    }

    //调用存储过程
    //sql call 过程 ??
    public static CallableStatement callPro1
    (String sql, String[] parameters) {
        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            if (parameters!=null) {
                for(int i=0; i<parameters.length; i++) {
                    cs.setObject(i + 1, parameters[i]);
                }
            }

            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不需要关闭
            close(rs, cs, ct);
        }
        return cs;
    }

    //同一的select
    //ResultSet->Arraylist
    public static ResultSet executeQuery(String sql, String[] parameters) {
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null && !parameters.equals("")) {
                for(int i=0; i<parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return rs;
    }

    public static void close(ResultSet rs, Statement ps, Connection ct) {
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ct!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getCt() {
        return ct;
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static CallableStatement getCs() {
        return cs;
    }
}
