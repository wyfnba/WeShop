package cn.edu.guet.WeShop.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
/*
 * Created by JFormDesigner on Sat Apr 02 15:41:52 CST 2022
 */

/**
 * @author 1
 */
public class Login extends JFrame {
    public static String user_id;
    public Login() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField("陶泽康");
        label2 = new JLabel();
//        textField2 = new JTextField("tzk");
        passwordField =new JPasswordField();
        button1 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(115, 90), label1.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(185, 85, 100, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(120, 125), label2.getPreferredSize()));
        contentPane.add(passwordField);
//        textField2.setBounds(185, 120, 100, textField2.getPreferredSize().height);
        passwordField.setBounds(185, 120, 100, passwordField.getPreferredSize().height);
        passwordField.setEchoChar('*');

        //---- button1 ----
        button1.addActionListener(
                (e) -> {
                    /*
                    1、拿到登录界面的用户名和密码
                    2、去和数据库中的用户名和密码比对
                    3、创建了数据库和表
                    4、添加一条记录
                    INSERT INTO sys_user (id,name,password) VALUES(1111,'guet','guet1234');
                     */
                    String username = textField1.getText();
                    String password = passwordField.getText();

                    String user = "root";
                    String dbPassword = "wyfnb666";
                    String url = "jdbc:mysql://47.94.211.86:3306/shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

                    Connection conn = null;
                    // 拼sql，容易有注入攻击
                    String sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";

                    System.out.println(sql);
                    ResultSet rs = null;//结果集：内存，存储了查询到的数据；内存区有一个游标，执行完查询的时候，不指向任何记录
                    Statement stmt = null;//语句对象，容易产生注入攻击

                    try {
                        conn = DriverManager.getConnection(url, user, dbPassword);

                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {//让游标向下移动一次
                            user_id=rs.getString(1);
                            System.out.println("登录成功");
                            this.setVisible(false);
                            if ("采购员".equals(rs.getString(4))){
                                BuyerInterface buyerInterface = new BuyerInterface(rs.getString(1));
                                buyerInterface.setVisible(true);
                            }else if("老板".equals(rs.getString(4))){
                                OrderList orderList=new OrderList();
                                orderList.setVisible(true);
                            }else{
                                SellMain sellMain =new SellMain();
                                sellMain.setVisible(true);
                            }

                        } else {
                            System.out.println("用户名或密码错误");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        button1.setText("\u767b\u5f55");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(170, 185), button1.getPreferredSize()));


        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);//设置组件可见
    }

    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
//    private JTextField textField2;
    private JPasswordField passwordField;
    private JButton button1;


    public static void main(String[] args) {
        new Login();
    }
}
