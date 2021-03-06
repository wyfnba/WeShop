package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.manager.ReplenishManager;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * @liwei
 */
public class ReplenishStock extends JFrame {
    String title;
    String user_id;
    LinkedHashMap<String,String> hm = new LinkedHashMap<>();
    List<String> item_ids = new ArrayList<>();
    List<Double> stocks = new ArrayList<>();
    List<Double> amounts = new ArrayList<>();
    List<String> list1;
    double money = 0;

    public ReplenishStock(String user_id,List<String> list) {
        this.user_id = user_id;
        this.list1 = list;
        initComponents();
    }

    public ReplenishStock(String title,String user_id,List<String> list) {
        this.user_id = user_id;
        this.title = title;
        this.list1 = list;
        initComponents();
    }

    public ReplenishStock(String user_id,LinkedHashMap<String,String> hm,List<String> item_ids,List<Double> stocks,List<Double> amounts,double money,List<String> list) {
        this.user_id = user_id;
        this.hm = hm;
        this.item_ids = item_ids;
        this.stocks = stocks;
        this.amounts = amounts;
        this.money = money;
        this.list1 = list;
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
        label1.setText("????????????");
        contentPane.add(label1);
        label1.setBounds(20, 20, 55, 20);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 130, 20);
        textField1.setText(title);

        //---- label2 ----
        label2.setText("?????????");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        //textField2.setText(String.valueOf(stock));

        /*//---- label3 ----
        label3.setText("????????????");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);
        //textField3.setText(String.valueOf(item.getPrice()));*/

        //---- label4 ----
        label4.setText("????????????");
        contentPane.add(label4);
        label4.setBounds(240, 80, 90, 20);
        contentPane.add(textField4);
        textField4.setBounds(300, 80, 130, 20);
        //textField4.setText(item.getDescription());

        /*//---- label5 ----
        label5.setText("?????????");
        contentPane.add(label5);
        label5.setBounds(20, 140, 55, 20);
        contentPane.add(textField5);
        textField5.setBounds(70, 140, 130, 20);
        //textField5.setText(String.valueOf(item.getSales()));*/

        /*//---- label6 ----
        label6.setText("????????????");
        contentPane.add(label6);
        label6.setBounds(240, 140, 90, 20);
        contentPane.add(textField6);
        textField6.setBounds(300, 140, 130, 20);
        //textField6.setText(item.getImg_url());*/

        //---- button1 ----
        button1.setText("???????????????");
        contentPane.add(button1);
        button1.setBounds(50, 300, 120, 30);
        button1.addActionListener(
                (e)->{
                    ResultSet rs1;
                    Connection conn;
                    String sql1 = "SELECT id FROM item WHERE title = ?";
                    String item_id;
                    double money = Double.parseDouble(textField4.getText());
                    double amount = Double.parseDouble(textField2.getText());
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);

                        //?????????textField1.getText()?????????
                        ps.setString(1,textField1.getText());
                        rs1 = ps.executeQuery();
                        ReplenishManager replenishManager = new ReplenishManager();
                        if (rs1.next()){
                            item_id = rs1.getString(1);
                            item_ids.add(item_id);//????????????????????????????????????
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();

                            //??????????????????????????????????????????
                            if (rs1.next()){
                                stocks.add(rs1.getDouble(1));//??????????????????????????????
                                amounts.add(amount);//??????????????????
                            }
                            String s = list1.get(0);
                            list1.remove(0);
                            hm.put(s,textField1.getText());//?????????????????????????????????????????????
                        }else{
                            item_id = UUID.randomUUID().toString().replace("-", "");
                            item_ids.add(item_id);////??????????????????????????????????????????id
                            amounts.add(amount);//
                            stocks.add((double) 0);//??????????????????????????????????????????????????????0
                            title = textField1.getText();

                            String s = list1.get(0);
                            list1.remove(0);
                            s = s + "yes";
                            hm.put(s,textField1.getText());//???????????????????????????????????????
                        }
                        replenishManager.PackagingClass(money,user_id,item_ids,amounts,stocks,hm);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // ??????UPDATE
                    this.setVisible(false);
                }
        );

        //button2
        button2.setText("??????????????????");
        contentPane.add(button2);
        button2.setBounds(200, 300, 120, 30);
        button2.addActionListener(
                (e)->{
                    ResultSet rs1;
                    Connection conn;
                    String sql1 = "SELECT id FROM item WHERE title = ?";
                    String item_id;
                    money = money + Double.parseDouble(textField4.getText());//money?????????????????????????????????
                    double amount = Double.parseDouble(textField2.getText());//???????????????
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);

                        //?????????textField1.getText()?????????
                        ps.setString(1,textField1.getText());
                        rs1 = ps.executeQuery();
                        ReplenishManager replenishManager;
                        if (rs1.next()){
                            item_ids.add(rs1.getString(1));//?????????????????????????????????????????????????????????id
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();

                            //????????????????????????????????????????????????????????????????????????????????????=????????????+?????????
                            if (rs1.next()){
                                stocks.add(rs1.getDouble(1));//???????????????????????????
                                amounts.add(amount);//???????????????????????????
                            }

                            String s = list1.get(0);
                            list1.remove(0);
                            hm.put(s,textField1.getText());//?????????????????????????????????????????????
                        }else{
                            item_id = UUID.randomUUID().toString().replace("-", "");
                            item_ids.add(item_id);//??????????????????????????????????????????id
                            amounts.add(amount);//??????????????????????????????????????????????????????
                            stocks.add((double) 0);//????????????????????????????????????0
                            title = textField1.getText();

                            String s = list1.get(0);
                            list1.remove(0);
                            s = s + "yes";
                            hm.put(s,textField1.getText());//??????????????????????????????????????????
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // ??????UPDATE
                    this.setVisible(false);

                    ReplenishStock rs = new ReplenishStock(user_id,hm,item_ids,stocks,amounts,money,list1);
                    rs.setVisible(true);
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
