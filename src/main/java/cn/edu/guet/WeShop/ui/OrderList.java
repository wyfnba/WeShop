package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Orderdetail;

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
    List<Orderdetail> list = new ArrayList<Orderdetail>();
    public OrderList() {
        initComponents();
    }

    private void initComponents() {
        List<Orderdetail> listc = new ArrayList<>();
        List<String> amount = new ArrayList<>();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label1 = new JLabel();

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
                Font("STHeiti Light", Font.BOLD, 30));
        label1.setText("订单列表");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);


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
        button2.setBounds(200, 355, 100, 30);
        button2.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    Sale_Return sale_return=new Sale_Return();
                    sale_return.setVisible(true);
                }
        );

        button3.setText("用户管理");
        contentPane.add(button3);
        button3.setBounds(350, 355, 100, 30);
        button3.addActionListener(
                (e)->{
                    this.setVisible(false);
                    UserControl userControl=new UserControl();
                    userControl.setVisible(true);
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

        List<Item> list = new ArrayList<Item>();
        Connection conn = null;
        String user = "root";
        String dbPassword = "wyfnb666";
        String url = "jdbc:mysql://47.94.211.86:3306/shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        Statement stmt = null;
        String sql = "SELECT * FROM orderdetail";
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, dbPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Orderdetail orderDetail = new Orderdetail();
                orderDetail.setOrderbase_id(rs.getString(1));
                orderDetail.setItem_id(rs.getString(2));
                orderDetail.setAmount(rs.getInt(3));
                this.list.add(orderDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        // 把集合的数据（商品信息）转换成二维数组
        data = new Object[this.list.size()][head.length];

        for (int i = 0; i < this.list.size(); i++) {
            data[i][0] = this.list.get(i).getOrderbase_id();
            data[i][1] = this.list.get(i).getItem_id();
            data[i][2] = this.list.get(i).getAmount();
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"订单基础表", "商品id", "商品数量"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;

    public static void main(String[] args) {
        new OrderList();
    }
}