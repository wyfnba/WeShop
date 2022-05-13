package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Orderbase;
import cn.edu.guet.WeShop.bean.Orderdetail;
import cn.edu.guet.WeShop.pay.WXPay;
import cn.edu.guet.WeShop.service.OrderService;
import cn.edu.guet.WeShop.service.impl.OrderServiceImpl;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static cn.edu.guet.WeShop.ui.Login.user_id;


/**
 * @liwei
 */
public class ShoppingCart extends JFrame {
    String orderNo;
    java.util.List<Item> list;
    java.util.List<String> amount;
    public static List<Orderdetail> orderdetailList=new ArrayList<>();
    public static double price=0;

    public static List<Orderdetail> getOrderdetailList(){
        return orderdetailList;
    }

    public static double getPrice(){
        return price;
    }

    public ShoppingCart(){

    }

    public ShoppingCart(java.util.List<Item> list, java.util.List<String> amount) {
        orderdetailList.clear();
        this.list = list;
        this.amount = amount;
        initComponents();
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();

        label1 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();

        DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table1.setModel(tableModel);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        label1.setFont(new
                Font("STHeiti Light", Font.BOLD,
                30));
        label1.setText("购物车");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);


        contentPane.add(textField2);
        textField2.setBounds(830, 355, 130, 30);

        button1.setText("扫码支付");
        contentPane.add(button1);
        button1.setBounds(720, 355, 100, 30);
        button1.addActionListener(
                e -> {
                    try {
                        for (int i=0;i<list.size();i++){
                            Orderdetail orderdetail=new Orderdetail("","",list.get(i).getId(),Integer.parseInt(amount.get(i)));
                            orderdetailList.add(orderdetail);
                            price=price+list.get(i).getPrice()*Double.parseDouble(amount.get(i));
                        }
                        orderNo=WXPay.scanCodeToPay(textField2.getText());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        button6.setText("生成订单");
        contentPane.add(button6);
        button6.setBounds(610, 355, 100, 30);
        button6.addActionListener(
                e -> {
                    String transactionId= UUID.randomUUID().toString().replace("-", "");
                    Orderbase orderbase=new Orderbase(1623889015,orderNo,transactionId,user_id,price);
                    for (int i=0;i<orderdetailList.size();i++){
                        orderdetailList.get(i).setOrderbase_id(orderbase.getId());
                    }
                    OrderService orderService=new OrderServiceImpl();
                    try {
                        orderService.addOrder(orderbase,orderdetailList);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
        );

        button2.setText("二维码支付");
        contentPane.add(button2);
        button2.setBounds(490, 355, 100, 30);
        button2.addActionListener(
                e -> {
                    for (int i=0;i<list.size();i++){
                        Orderdetail orderdetail=new Orderdetail("","",list.get(i).getId(),Integer.parseInt(amount.get(i)));
                        orderdetailList.add(orderdetail);
                        price=price+list.get(i).getPrice()*Double.parseDouble(amount.get(i));
                    }
                    WXPay.unifiedOrder();
                    Pay pay = new Pay();
                    pay.setVisible(true);
                }
        );

        contentPane.add(textField1);
        textField1.setBounds(270, 355, 130, 30);

        button5.setText("返回");
        contentPane.add(button5);
        button5.setBounds(30, 355, 100, 30);
        button5.addActionListener(
                e -> {
                    this.setVisible(false);
                    SellMain sellMain=new SellMain();
                    sellMain.setVisible(true);
                }
        );

        button4.setText("修改数量");
        contentPane.add(button4);
        button4.setBounds(150, 355, 100, 30);
        button4.addActionListener(
                (e)->{
                    int rowNo = table1.getSelectedRow();
                    Item item = new Item();
                    double amo = Double.parseDouble(amount.get(rowNo));
                    System.out.println(amo);//得到amount表里的数量
                    double amountchange = Double.parseDouble(textField1.getText());
                    System.out.println(amountchange);//得到输入框里的数量
                    amount.set(rowNo,(textField1.getText()));
                    System.out.println(amo);//修改amount表里的数量
                    //
                    String text = "0";
                    for (int i = 0; i < amount.size(); i++) {
                        if (amount.get(i) ==text) {
                            amount.remove(i);
                            list.remove(i);
                            System.out.println(amount.get(i));
                            System.out.println(list.get(i));
                        }
                    }
                    //删除操作
                    amount.set(rowNo, String.valueOf(amountchange));
                    ShoppingCart shoppingCart = new ShoppingCart(list,amount);
                    this.setVisible(false);
                    shoppingCart.setVisible(true);
                }
        );

        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 50, 1000, 300);
        {
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
        this.setBounds(300, 300, 1000, 415);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Object[][] getDataFromDatabase() {

        //java.util.List<Item> list = new ArrayList<Item>();

        // 把集合的数据（商品信息）转换成二维数组
        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getTitle();
                data[i][1] = list.get(i).getPrice();
                data[i][2] = list.get(i).getDescription();
                data[i][3] = amount.get(i);
            }
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"商品名称", "价格", "描述", "数量"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
}