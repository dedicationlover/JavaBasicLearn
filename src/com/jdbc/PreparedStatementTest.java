package com.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PreparedStatementTest
{
    private String driver;
    private String url;
    private String user;
    private String pass;
    Connection conn;
    Statement stmt;
    PreparedStatement pstmt;

    public void initParam(String paramFile) throws Exception
    {
        // 使用Properties类来加载属性文件
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    } // end initParam

    public void insertUseStatement() throws Exception
    {
        long start = System.currentTimeMillis();
        try
        {
            // 使用Connection来创建一个Statement对象
            stmt = conn.createStatement();
            // 需要使用100条SQL语句来插入100条记录
            for (int i = 0; i < 100; i++)
            {
                stmt.executeUpdate("insert into student_table values(null,'姓名" + i + "', 1)");
            }
            System.out.println("使用Statement费时：" +
                    (System.currentTimeMillis() - start));
        }
        finally
        {
            if (stmt != null)
            {
                stmt.close();
            }
        } // end try
    } // end insertUseStatement

    public void insertUsePrepare() throws Exception
    {
        long start = System.currentTimeMillis();
        try
        {
            // 使用Connection来创建一个PreparedStatement对象
            pstmt = conn.prepareStatement("insert into student_table values(null, ?, 1)");
            // 100此为PreparedStatement的参数设值，就可以插入100条记录
            for (int i = 0; i < 100; i++)
            {
                pstmt.setString(1, "姓名" + i);
                pstmt.executeUpdate();
            }
            System.out.println("使用PreparedStatement费时："
                    + (System.currentTimeMillis() - start));
        }
        finally
        {
            if (pstmt != null)
            {
                pstmt.close();
            }
        } // end try
    } // end insertUsePrepare

    // 定义打开连接的方法
    public void getConn() throws Exception
    {
        if (conn == null)
        {
            // 加载驱动
            Class.forName(driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
        } // end if
    } // end getConn

    // 定义关闭连接的方法
    public void closeConn() throws Exception
    {
        if (conn != null)
        {
            conn.close();
        }
    } // end closeConn

    public static void main(String[] args) throws Exception
    {
        PreparedStatementTest pt = null;
        try
        {
            pt = new PreparedStatementTest();
            pt.initParam("mysql.ini");
            pt.getConn();
            pt.insertUseStatement();
            pt.insertUsePrepare();
        }
        finally
        {
            pt.closeConn();
        } // end try
    } // end main
} // end PreparedStatementTest
