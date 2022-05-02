package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.manager.ReplenishManager;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @liwei
 */
public class ReplenishStock extends JFrame {
    List<String> title = new ArrayList<>();
    String user_id;
    List<String> item_id = new ArrayList<>();
    List<Double> amount = new ArrayList<>();
    double money = 0;
    List<Double> stock = new ArrayList<>();

    public ReplenishStock(String user_id) {
        this.user_id = user_id;
        this.title.add("");
        initComponents();
    }

    public ReplenishStock(String title,String user_id) {
        this.user_id = user_id;
        this.title.add(title);
        initComponents();
    }

    public ReplenishStock(String user_id,List<String> title,List<String> item_id,List<Double> amount,double money,List<Double> stock) {
        this.title = title;
        this.item_id = item_id;
        this.amount = amount;//把上一个进货单的数据获取下来
        this.user_id = user_id;
        this.money = money;
        this.stock = stock;
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
        textField1.setText(title.get(0));

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
        button1.setBounds(50, 300, 100, 30);
        button1.addActionListener(
                (e)->{

                    ResultSet rs1;
                    Connection conn;
                    String sql1 = "SELECT id FROM item WHERE title = ?";

                    money = Double.parseDouble(textField4.getText())+money;
                    amount.add(Double.parseDouble(textField2.getText()));//把要进货的数量添加到集合中

                    if (title.size() == 1 && "".equals(title.get(0))){
                        title.set(0,textField1.getText());
                    }else{
                        title.add(textField1.getText());
                    }
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);
                        ps.setString(1,title.get(title.size()-1));//查询的应该是最后获取到的商品名称
                        rs1 = ps.executeQuery();
                        ReplenishManager replenishManager = null;

                        if (rs1.next()){
                            item_id.add(rs1.getString(1));
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();

                            //如果库存表已经有该商品的记录，那就添加记录：库存记录=库存记录+进货量
                            if (rs1.next())stock.add(Double.parseDouble(textField2.getText()));

                            replenishManager = new ReplenishManager(false);//false表示商品表不用新增商品
                        }else{
                            String id = UUID.randomUUID().toString().replace("-", "");
                            item_id.add(id);
                            stock.add(Double.parseDouble(textField2.getText()));//如果还没有该商品，则进货量即为库存量
                            replenishManager = new ReplenishManager(true, title);
                        }

                        replenishManager.PackagingClass(money,user_id,item_id,amount,stock);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // 执行UPDATE
                    this.setVisible(false);
                }
        );

        button2.setText("添加进货商品");
        contentPane.add(button2);
        button2.setBounds(200, 300, 120, 30);
        button2.addActionListener(
                (e)->{
                    money = money+Double.parseDouble(textField4.getText());
                    amount.add(Double.parseDouble(textField2.getText()));

                    if (title.size() == 1 && "".equals(title.get(0))){
                        title.set(0,textField1.getText());
                    }else{
                        title.add(textField1.getText());
                    }
                    //以下主要是用于添加“stock”这个集合元素的代码
                    ResultSet rs1;
                    Connection conn;
                    String sql1 = "SELECT id FROM item WHERE title = ?";
                    try {
                        conn = ConnectionHandler.getConn();
                        PreparedStatement ps = conn.prepareStatement(sql1);
                        ps.setString(1,title.get(title.size()-1));//查询的应该是最后获取到的商品名称
                        rs1 = ps.executeQuery();
                        if (rs1.next()){
                            item_id.add(rs1.getString(1));
                            String sql2 = "SELECT stock FROM item_stock WHERE item_id = ?";
                            ps = conn.prepareStatement(sql2);
                            ps.setString(1,rs1.getString(1));
                            rs1 = ps.executeQuery();
                            //如果库存表已经有该商品的记录，那就添加记录：库存记录=库存记录+进货量
                            if (rs1.next())stock.add(Double.parseDouble(textField2.getText()));
                        }else{
                            item_id.add(UUID.randomUUID().toString().replace("-", ""));
                            stock.add(Double.parseDouble(textField2.getText()));//如果还没有该商品，则进货量即为库存量
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                    this.setVisible(false);


                    //如果要再加进货单，则把当前的数据传递下去
                    ReplenishStock rs = new ReplenishStock(user_id,title,item_id,amount,money,stock);
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
