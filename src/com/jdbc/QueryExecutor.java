package com.jdbc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * 查询执行器（用户在文本框内输入合法的查询语句执行成功后，程序下面的表格将会显示查询结果）
 */
public class QueryExecutor
{

    JFrame jf = new JFrame("查询执行器");
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton execBn = new JButton("查询");
    // 用于输入查询语句的文本框
    private JTextField sqlField = new JTextField(45);
    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;

    // 采用静态初始化块来初始化Connection、Statement对象
    static
    {
        try
        {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("mysql.ini");
            props.load(in);
            in.close();
            String drivers = props.getProperty("driver");
            String url = props.getProperty("url");
            String username = props.getProperty("user");
            String password = props.getProperty("pass");
            // 加载数据库驱动
            Class.forName(drivers);
            // 取得数据库连接
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } // end
    } // end static

    // ------------初始化界面的方法-------------
    public void init()
    {
        JPanel top = new JPanel();
        top.add(new JLabel("输出查询语句："));
        top.add(sqlField);
        top.add(execBn);
        // 为执行按钮、单行文本框添加事件监听器
        execBn.addActionListener(new ExceListener());
        sqlField.addActionListener(new ExceListener());
        jf.add(top, BorderLayout.NORTH);
        jf.setSize(640, 480);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    } // end init

    // 定义监听器
    class ExceListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // 删除原来的JTable（JTable使用scrollPane来包装）
            if (scrollPane != null)
            {
                jf.remove(scrollPane);
            }
            try
            {
                // 根据用户输入的SQL执行查询
                rs = stmt.executeQuery(sqlField.getText());
                // 取出ResultSet的MetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                Vector<String> columnNames = new Vector<String>();
                Vector data = new Vector();
                // 把ResultSet的所有列名添加到Vector里
                for (int i = 0; i < rsmd.getColumnCount(); i++)
                {
                    columnNames.add(rsmd.getCatalogName(i + 1));
                }
                // 把ResultSet的所有记录添加到Vector里
                while (rs.next())
                {
                    Vector v = new Vector();
                    for (int i = 0; i < rsmd.getColumnCount(); i++)
                    {
                        v.add(rs.getString(i + 1));
                    }
                    data.add(v);
                } // end while
                // 更新TableModel
                model = new DefaultTableModel(data, columnNames);
                // 创建新的JTable
                JTable table = new JTable(model);
                scrollPane = new JScrollPane(table);
                // 添加新的Table
                jf.add(scrollPane);
                // 更新主窗口
                jf.validate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            } // end try
        } // end actionPerformed
    } // end ExceListener

    public static void main(String[] args)
    {
        new QueryExecutor().init();
    }
} // end QueryExecutor
