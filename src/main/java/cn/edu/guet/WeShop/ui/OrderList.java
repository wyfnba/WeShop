package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.TableSearch.SumMoney;
import cn.edu.guet.WeShop.TableSearch.User_Orderbase;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Orderdetail;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @liwei
 */
public class OrderList extends JFrame {
    public OrderList() {
        initComponents();
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4=new JButton();
        label1 = new JLabel();
        label2=new JLabel();
        label3=new JLabel();
        label4=new JLabel();
        label5=new JLabel();

        DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table1.setModel(tableModel);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        label1.setFont(new Font("STHeiti Light", Font.BOLD, 30));
        label1.setText("订单列表");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);

        label2.setFont(new Font("宋体",Font.BOLD,15));
        SumMoney sumMoney=new SumMoney();
        Double IncomingMoney=sumMoney.IncomingMoney();
        Double ReturnMoney=sumMoney.ReturnMoney();
        Double OrderMoney=sumMoney.OrderMoney();
        Double Sum=OrderMoney+IncomingMoney-ReturnMoney;
        label2.setText(String.valueOf(Sum));
        contentPane.add(label2);
        label2.setBounds(500,355,100,30);

        label3.setFont(new Font("宋体",Font.BOLD,15));
        label3.setText("总收益：");
        contentPane.add(label3);
        label3.setBounds(400,355,70,30);

        label4.setFont(new Font("宋体",Font.BOLD,15));
        label4.setText("订单收益:");
        contentPane.add(label4);
        label4.setBounds(650,355,70,30);

        label5.setFont(new Font("宋体",Font.BOLD,15));
        label5.setText(String.valueOf(OrderMoney));
        contentPane.add(label5);
        label5.setBounds(720,355,100,30);

        button1.setText("进货表");
        contentPane.add(button1);
        button1.setBounds(20, 355, 100, 30);
        button1.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    Sale_Stock sale_stock=new Sale_Stock();
                    sale_stock.setVisible(true);
                }
        );


        button2.setText("退货表");
        contentPane.add(button2);
        button2.setBounds(120, 355, 100, 30);
        button2.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    Sale_Return sale_return=new Sale_Return();
                    sale_return.setVisible(true);
                }
        );

        button3.setText("用户管理");
        contentPane.add(button3);
        button3.setBounds(250, 355, 100, 30);
        button3.addActionListener(
                (e)->{
                    this.setVisible(false);
                    UserControl userControl=new UserControl();
                    userControl.setVisible(true);
                }
        );

        button4.setText("刷新");
        contentPane.add(button4);
        button4.setBounds(800,355,100,30);
        button4.addActionListener(
                (e)->{
                    DefaultTableModel Refresh_tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    table1.setModel(Refresh_tableModel);
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
        this.setBounds(300, 300, 1000, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Object[][] getDataFromDatabase() {
        List<User_Orderbase> list = new ArrayList<User_Orderbase>();
        Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT u.username,u.`status`,o.order_price,o.time_end\n" +
                "FROM user u,orderbase o\n" +
                "WHERE u.id=o.user_id";
        ResultSet rs = null;
        try {
            conn = ConnectionHandler.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User_Orderbase user_orderbase=new User_Orderbase();
                user_orderbase.setUserName(rs.getString(1));
                user_orderbase.setStatus(rs.getString(2));
                user_orderbase.setPrice(rs.getDouble(3));
                user_orderbase.setTime(rs.getTimestamp(4));
                list.add(user_orderbase);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } /*finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
        // 把集合的数据（商品信息）转换成二维数组
        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getUserName();
            data[i][1] = list.get(i).getStatus();
            data[i][2] = list.get(i).getPrice();
            data[i][3] = list.get(i).getTime();
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"经手人姓名", "职称", "交易金额","交易时间"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    public static void main(String[] args) {
        new OrderList();
    }
}