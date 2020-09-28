package com.jdbc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginFrame {

    private final String PROP_FILE = "mysql.ini";
    // driver 是数据库驱动类，通过查驱动文档得到
    private String driver;
    // url是数据库的服务地址
    private String url;
    private String user;
    private String pass;
    // 登录界面的GUI组件
    private JFrame jf = new JFrame("登录");
    private JTextField userField = new JTextField(20);
    private JTextField passField = new JTextField(20);
    private JButton loginButton = new JButton("登录");
    // 执行JDBC操作的对象
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public void init() throws Exception{
        Properties connProp = new Properties();
        connProp.load(new FileInputStream(PROP_FILE));
        driver = connProp.getProperty("driver");
        url = connProp.getProperty("url");
        user = connProp.getProperty("user");
        pass = connProp.getProperty("pass");
        // 为登录按钮添加事件监听器
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // 登录成功则显示"登录成功"
                if(validate(userField.getText(), passField.getText())){
                    JOptionPane.showMessageDialog(jf, "登录成功");
                }
                // 否则显示“登录失败”
                else {
                    JOptionPane.showMessageDialog(jf, "登录失败");
                } // end if
            } // end actionPerformed
        });
        jf.add(userField, BorderLayout.NORTH);
        jf.add(passField);
        jf.add(loginButton, BorderLayout.SOUTH);
        jf.pack();
        jf.setVisible(true);
    } // end init
    private boolean validate(String userName, String userPass){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
//            stmt = conn.createStatement();
            /*
            select * from jdbc_test where jdbc_name = '' or true or '' and jdbc_desc = ''
            也会登录成功！SQL把这个true当成了直接量。
            */
//            String sql = "select * from jdbc_test "
//                    + "where jdbc_name = '" + userName
//                    + "' and jdbc_desc = '" + userPass + "'";
//            System.out.println(sql);
            // 如果查询的ResultSet里有超过一条的记录，则登录成功

//            if(stmt.executeQuery(sql).next()){
//                return true;
//            } // end if


            pstmt = conn.prepareStatement("select * from jdbc_test "
                    + "where jdbc_name = ? and jdbc_desc = ?");
            pstmt.setString(1, userName);
            pstmt.setString(2, userPass);
            // 如果查询的ResultSet里有超过一条的记录，则登录成功
            if(pstmt.executeQuery().next()){
                return true;
            } // end if

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    conn.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } // end try
        return false;
    } // end validate
    public static void main(String[] args) throws Exception {
        new LoginFrame().init();
    }
} // end LoginFrame

