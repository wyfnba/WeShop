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
        label1.setText("商品名称");
        contentPane.add(label1);
        label1.setBounds(20, 20, 55, 20);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 130, 20);
        textField1.setText(title);

        //---- label2 ----
        label2.setText("进货量");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        //textField2.setText(String.valueOf(stock));

        /*//---- label3 ----
        label3.setText("商品单价");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);
        //textField3.setText(String.valueOf(item.getPrice()));*/

        //---- label4 ----
        label4.setText("商品总价");
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
        button1.setText("生成进货单");
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

                        //这里的textField1.getText()不能用
                        ps.setString(1,textField1.getText());
                        rs1 = ps.executeQuery();
                        ReplenishManager replenishManager = new ReplenishManager();
                        if (rs1.next()){
                            item_id = rs1.getString(1);
                            item_ids.add(item_id);//如果商品表存在该商品，则
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();

                            //如果库存表已经有该商品的记录
                            if (rs1.next()){
                                stocks.add(rs1.getDouble(1));//将商品的库存记录下来
                                amounts.add(amount);//将进货量记录
                            }
                            String s = list1.get(0);
                            list1.remove(0);
                            hm.put(s,textField1.getText());//表示当前的商品在库存表已经存在
                        }else{
                            item_id = UUID.randomUUID().toString().replace("-", "");
                            item_ids.add(item_id);////如果没有该商品就生成一个商品id
                            amounts.add(amount);//
                            stocks.add((double) 0);//如果没有该商品，则记录该商品的库存为0
                            title = textField1.getText();

                            String s = list1.get(0);
                            list1.remove(0);
                            s = s + "yes";
                            hm.put(s,textField1.getText());//代表要将该商品添加到商品表
                        }
                        replenishManager.PackagingClass(money,user_id,item_ids,amounts,stocks,hm);
                        //replenishManager.PackagingClass(money,user_id,item_id,amount,stock);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // 执行UPDATE
                    this.setVisible(false);
                }
        );

        //button2
        button2.setText("添加进货商品");
        contentPane.add(button2);
        button2.setBounds(200, 300, 120, 30);
        button2.addActionListener(
                (e)->{
                    ResultSet rs1;
                    Connection conn;
                    String sql1 = "SELECT id FROM item WHERE title = ?";
                    String item_id;
                    money = money + Double.parseDouble(textField4.getText());//money存的是本次进货总的花费
                    double amount = Double.parseDouble(textField2.getText());//获取进货量
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);

                        //这里的textField1.getText()不能用
                        ps.setString(1,textField1.getText());
                        rs1 = ps.executeQuery();
                        ReplenishManager replenishManager;
                        if (rs1.next()){
                            item_ids.add(rs1.getString(1));//如果商品表里存在该商品，则记录该商品的id
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();

                            //如果库存表已经有该商品的记录，那就把记录替换成：库存记录=库存记录+进货量
                            if (rs1.next()){
                                stocks.add(rs1.getDouble(1));//记录该商品的库存值
                                amounts.add(amount);//记录该商品的进货量
                            }

                            String s = list1.get(0);
                            list1.remove(0);
                            hm.put(s,textField1.getText());//表示当前的商品在库存表已经存在
                        }else{
                            item_id = UUID.randomUUID().toString().replace("-", "");
                            item_ids.add(item_id);//如果没有该商品就生成一个商品id
                            amounts.add(amount);//如果还没有该商品，则进货量即为库存量
                            stocks.add((double) 0);//不存在的商品，则其库存为0
                            title = textField1.getText();

                            String s = list1.get(0);
                            list1.remove(0);
                            s = s + "yes";
                            hm.put(s,textField1.getText());//表示当前商品需要添加进商品表
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // 执行UPDATE
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
