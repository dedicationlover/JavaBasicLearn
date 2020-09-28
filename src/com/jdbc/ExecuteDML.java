package com.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 执行一条insert语句，会向刚刚建立的jdbc_test数据表中插入几条记录，使用带子查询的insert语句
 */
public class ExecuteDML
{
    private String driver;
    private String url;
    private String user;
    private String pass;
    Connection conn;
    Statement stmt;

    public void initParam(String paramFile) throws Exception
    {
        // 使用Properties类来加载属性文件
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }

    public int insertData(String sql) throws Exception
    {
        try
        {
            // 加载驱动
            Class.forName(driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
            // 使用Connection来创建一个Statement对象
            stmt = conn.createStatement();
            // 执行DML，返回受影响的记录条数
            return stmt.executeUpdate(sql);
        }
        finally
        {
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

    public static void main(String[] args) throws Exception
    {
        ExecuteDML ed = new ExecuteDML();
        ed.initParam("mysql.ini");
        int result = ed.insertData("insert into jdbc_test (jdbc_name,jdbc_desc) "
                + "select s.student_name, t.teacher_name "
                + "from student_table s, teacher_table t "
                + "where s.java_teacher = t.teacher_id;");
        System.out.println("-----系统中共有" + result + "条记录受影响-----");

    }
}
