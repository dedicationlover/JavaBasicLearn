package com.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 使用executeUpdate方法创建数据表。没有直接把数据库连接信息写在程序里，
 * 而使用一个mysql.ini文件（properties文件）来保存数据库连接信息——
 * 把应用程序从开发环境移植到生产环境时，无须修改源代码，只需要修改mysql.ini配置文件即可
 */
public class ExecuteDDL {

    private String driver;
    private String url;
    private String user;
    private String pass;
    public void initParam(String paramFile) throws Exception{
        // 使用Properties类来加载属性文件
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    } // end initParam
    public void createTable(String sql) throws Exception{
        Connection conn = null;
        Statement stmt = null;
        try {
            // 加载驱动
            Class.forName(driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
            // 使用Connection来创建一个Statment对象
            stmt = conn.createStatement();
            // 执行DDL，创建数据表
            stmt.executeUpdate(sql);
        } finally {
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        } // end try
    } // end createTable
    public static void main(String[] args) throws Exception {
        ExecuteDDL ed = new ExecuteDDL();
        ed.initParam("mysql.ini");
        ed.createTable("create table jdbc_test "
                + "( jdbc_id int auto_increment primary key, "
                + "jdbc_name varchar(255),"
                + "jdbc_desc text);");
        System.out.println("--------建表成功--------");
    }
} // end ExecuteDDL

