package com.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 分析当前Connection连接对应数据库的基本信息
 */
public class DatabaseMetaDataTest
{
    private String driver;
    private String url;
    private String user;
    private String pass;
    Connection conn;
    ResultSet rs;

    public void initParam(String paramFile) throws IOException
    {
        // 使用Properties类来加载属性文件
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }

    public void info() throws SQLException, ClassNotFoundException
    {
        try
        {
            // 加载驱动
            Class.forName("driver");
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
            // 获取DatabaseMetaData对象
            DatabaseMetaData dbmd = conn.getMetaData();
            // 获取MySQL支持的所有表类型
            ResultSet rs = dbmd.getTableTypes();
            System.out.println("----MySQL支持的表类型信息---");
            printResultSet(rs);

            // 获取当前数据库的全部数据表
            rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("----当前数据库里的数据表信息----");
            printResultSet(rs);

            // 获取student_table表的主键
            rs = dbmd.getPrimaryKeys(null, null, "student_table");
            System.out.println("----student_table表的主键信息----");
            printResultSet(rs);

            // 获取当前数据库的全部存储过程
            rs = dbmd.getProcedures(null, null, "%");
            System.out.println("----当前数据列里的存储过程信息----");
            printResultSet(rs);

            // 获取teacher_table表和student_table之间的外键约束
            rs = dbmd.getCrossReference(null, null, "teacher_table", null, null, "student_table");
            System.out.println("----teacher_table表和student_table之间的外键约束----");
            printResultSet(rs);

            // 获取student_table表的全部数据列
            rs = dbmd.getColumns(null, null, "student_table", "%");
            System.out.println("----student_table表的全部数据列----");
            printResultSet(rs);
        }
        finally
        {
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
    }

    public void printResultSet(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        // 打印ResultSet的所有列标题
        for (int i = 0; i < rsmd.getColumnCount(); i++)
        {
            System.out.print(rsmd.getCatalogName(i + 1) + "\t");
        }
        System.out.print("\n");
        // 打印ResultSet里的全部数据
        while (rs.next())
        {
            for (int i = 0; i < rsmd.getColumnCount(); i++)
            {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.print("\n");
        }
        rs.close();
    } // end printResultSet

    public static void main(String[] args) throws Exception
    {
        DatabaseMetaDataTest dt = new DatabaseMetaDataTest();
        dt.initParam("mysql.ini");
        dt.info();
    }

}
