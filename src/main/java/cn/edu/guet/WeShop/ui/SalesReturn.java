package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.manager.ReturnsManager;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @liwei
 */
public class SalesReturn extends JFrame {
    String title;
    String user_id;
    List<Double> stocks = new ArrayList<>();
    List<Double> amounts = new ArrayList<>();
    List<String> item_ids = new ArrayList<>();
    double money = 0;//money记录的是本次退货总共得到的钱

    public SalesReturn(String user_id) {
        this.user_id = user_id;
        initComponents();
    }

    public SalesReturn(String user_id,String title) {
        this.user_id = user_id;
        this.title = title;
        initComponents();
    }

    public SalesReturn(String user_id,List<Double> stocks,List<Double> amounts,List<String> item_ids,double money) {
        this.user_id = user_id;
        this.stocks = stocks;
        this.amounts = amounts;
        this.item_ids = item_ids;
        this.money = money;
        initComponents();
    }


    private void initComponents() {
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        label6 = new JLabel();
        textField6 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("商品名称");
        contentPane.add(label1);
        label1.setBounds(20, 20, 55, 20);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 130, 20);
        textField1.setText(title);

        /*//---- label2 ----
        label2.setText("剩余库存");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        textField2.setText(String.valueOf(stock));*/

        //---- label3 ----
        label3.setText("退货量");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);
        //textField3.setText(String.valueOf(item.getPrice()));

        //---- label4 ----
        label4.setText("退货总价");
        contentPane.add(label4);
        label4.setBounds(240, 80, 90, 20);
        contentPane.add(textField4);
        textField4.setBounds(300, 80, 130, 20);
        //textField4.setText(item.getDescription());

        /*//---- label5 ----
        label5.setText("促销价");
        contentPane.add(label5);
        label5.setBounds(20, 140, 55, 20);
        contentPane.add(textField5);
        textField5.setBounds(70, 140, 130, 20);
        //textField5.setText(String.valueOf(item.getSales()));*/

        /*//---- label6 ----
        label6.setText("商品图片");
        contentPane.add(label6);
        label6.setBounds(240, 140, 90, 20);
        contentPane.add(textField6);
        textField6.setBounds(300, 140, 130, 20);
        //textField6.setText(item.getImg_url());*/

        //---- button1 ----
        button1.setText("生成退货单");
        contentPane.add(button1);
        button1.setBounds(50, 300, 120, 30);
        button1.addActionListener(
                (e)->{
                    String sql1 = "SELECT id FROM item WHERE title = ?";
                    ResultSet rs1;
                    Connection conn;
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);
                        ps.setString(1,textField1.getText());
                        rs1 = ps.executeQuery();
                        ReturnsManager rm = new ReturnsManager(user_id);
                        if (rs1.next()){
                            item_ids.add(rs1.getString(1));
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();
                            if (rs1.next()){
                                if (rs1.getDouble(1)<Double.parseDouble(textField3.getText())){
                                    //如果要退货的量大于库存的量
                                    item_ids.remove(item_ids.size()-1);
                                    this.setVisible(false);
                                    SalesReturn sr = new SalesReturn(user_id,stocks,amounts,item_ids,money);
                                    sr.setVisible(true);
                                    Reform reform = new Reform("库存不足，无法完成退货，请重新输入");
                                    reform.setVisible(true);
                                    //如果走这句话，则所有的集合都
                                }else{
                                    stocks.add(rs1.getDouble(1));
                                    amounts.add(Double.valueOf(textField3.getText()));
                                    money = money + Double.parseDouble(textField4.getText());
                                    this.setVisible(false);
                                    rm.PackingClass(item_ids,stocks,amounts,money);
                                }
                            }
                        }else{
                            this.setVisible(false);
                            SalesReturn sr = new SalesReturn(user_id,stocks,amounts,item_ids,money);
                            sr.setVisible(true);
                            Reform reform = new Reform("要退货的商品不存在，请重新选择");
                            reform.setVisible(true);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
        );

        //---- button2 ----
        button2.setText("添加退货商品");
        contentPane.add(button2);
        button2.setBounds(200, 300, 120, 30);
        button2.addActionListener(
                (e)->{
                    String sql1 = "SELECT id FROM item WHERE title = ?";
                    ResultSet rs1;
                    Connection conn;
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);
                        ps.setString(1,textField1.getText());
                        rs1 = ps.executeQuery();
                        if (rs1.next()){
                            item_ids.add(rs1.getString(1));
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();
                            if (rs1.next()){
                                if (rs1.getDouble(1)<Double.parseDouble(textField3.getText())){
                                    //如果要退货的量大于库存的量
                                    item_ids.remove(item_ids.size()-1);
                                    System.out.println("库存不足，无法完成退货，请重新输入");
                                    //如果走这句话，则所有的集合都
                                }else{
                                    stocks.add(rs1.getDouble(1));
                                    amounts.add(Double.valueOf(textField3.getText()));
                                    money = money + Double.parseDouble(textField4.getText());
                                }
                            }
                        }else{
                            System.out.println("要退货的商品不存在，请重新选择");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    this.setVisible(false);
                    SalesReturn sr = new SalesReturn(user_id,stocks,amounts,item_ids,money);
                    sr.setVisible(true);
                }
        );



        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        this.setBounds(600, 300, 480, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label6;
    private JTextField textField6;
    private JButton button1;
    private JButton button2;
}
