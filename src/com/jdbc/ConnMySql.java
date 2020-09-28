package com.jdbc;

import java.sql.*;

public class ConnMySql
{
    public static void main(String[] args) throws Exception
    {
        // 1. 加载驱动，使用反射
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 使用DriverManager获取数据库连接
        // 其中返回的Connection就代表了Java程序和数据库的连接
        // 不同数据库的URL写法需要查询驱动文档，用户名、密码由DBA分配
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/mydatabase",
                "root", "mysql");
        // 3. 使用Connection来创建一个Sataement对象
        Statement stmt = conn.createStatement();
        // 4. 执行SQL语句
        /*
         * Statement有三种执行sql语句的方法：
         * 1. execute可执行任何SQL语句。--返回一个boolean值，
         * 如果执行后第一个结果是ResultSet,则返回true，否则返回false
         * 2. executeQuery 执行Select语句 --返回查询到的结果集
         * 3. executeUpdate 用于执行DML语句。 --返回一个整数代表被SQL语句影响的记录条数
         */
        ResultSet rs = stmt.executeQuery("select * from person");
        // ResultSet有系列的getXxx（列索引 | 列名），用于获取记录指针指向行、特定列的值
        // 不断地使用next将记录指针下移一行，如果依然指向有效行，则指针指向行的记录
        while (rs.next())
        {
            System.out.println(rs.getInt(1) + " "
                    + rs.getString(2) + " "
                    + rs.getString(3));
        }

        // 关闭数据库资源
        if (rs != null)
        {
            rs.close();
        }
        if (stmt != null)
        {
            stmt.close();
        }
        if (conn != null)
        {
            conn.close();
        }
    }
}
