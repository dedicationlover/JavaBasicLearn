package com.jdbc;

/**
 * 可滚动、可更新的结果集
 */

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class ResultSetTest
{

    private String driver;
    private String url;
    private String user;
    private String pass;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

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

    public void query(String sql) throws Exception
    {
        try
        {
            // 加载驱动
            Class.forName(driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
            // 使用Connection来创建一个PreparedStatement对象
            // 传入控制结果集可滚动，可更新的参数
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            rs.last();
            int rowCount = rs.getRow();
            for (int i = rowCount; i > 0; i--)
            {
                rs.absolute(i);
                System.out.println(rs.getString(1) + "\t"
                        + rs.getString(2) + "\t" + rs.getString(3));
                // 修改记录指针所有记录、第二列的值
                rs.updateString(2, "学生名" + i);
                // 提交修改
                rs.updateRow();
            } // end for
        }
        finally
        {
            if (rs != null)
            {
                rs.close();
            }
            if (pstmt != null)
            {
                pstmt.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        } // end try
    } // end query

    public static void main(String[] args) throws Exception
    {
        ResultSetTest rt = new ResultSetTest();
        rt.initParam("mysql.ini");
        rt.query("select * from student_table");
    }
} // end ResultSetTest

