package com.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 使用Statement的execute方法来执行任意的SQL语句，执行不同SQL语句时产生不同的输出
 */
public class ExecuteSQL
{
    private String driver;
    private String url;
    private String user;
    private String pass;
    Connection conn;
    Statement stmt;
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

    public void executeSql(String sql) throws Exception{
        try {
            // 加载驱动
            Class.forName(driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
            // 使用Connection来创建一个Statement对象
            stmt = conn.createStatement();
            // 执行SQL，返回Boolean值表示是否包含ResultSet
            boolean hasResultSet = stmt.execute(sql);
            // 如果执行后有ResultSet结果集
            if(hasResultSet){
                // 获取结果集
                rs = stmt.getResultSet();
                // ResultSetMetaData是用于分析结果集的元数据接口
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                // 迭代输出ResultSet对象
                while(rs.next()){
                    // 依次输出每列的值
                    for(int i = 0; i < columnCount; i++){
                        // 参数索引从1开始
                        System.out.print(rs.getString(i + 1) + "\t");
                    }
                    System.out.println("\n");
                } // end while
            } else{
                System.out.println("该SQL语句影响的记录有" + stmt.getUpdateCount() + "条");
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        } // end try
    } // end executeSql

    public static void main(String[] args) throws Exception {
        ExecuteSQL es = new ExecuteSQL();
        es.initParam("mysql.ini");
        System.out.println("---执行删除表的DDL语句---");
        es.executeSql("drop table if exists my_test");
        System.out.println("---执行建表的DDL语句---");
        es.executeSql("create table my_test"
                + "(test_id int auto_increment primary key,"
                + "test_name varchar(255))");
        System.out.println("---执行插入数据的DML语句---");
        es.executeSql("insert into my_test(test_name)"
                + "select student_name from student_table");
        System.out.println("---执行查询数据的查询语句--");
        es.executeSql("select * from my_test");
    }

}
