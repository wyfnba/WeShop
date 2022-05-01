package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * @liwei
 */
public class SellMain extends JFrame {
    java.util.List<Item> list = new ArrayList<Item>();
    public SellMain() {
        initComponents();
    }

    private void initComponents() {
        java.util.List<Item> listc = new ArrayList<Item>();
        java.util.List<String> amount = new ArrayList<>();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField("1");

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
        label1.setText("商品信息");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);


        label2.setText("数量：");
        contentPane.add(label2);
        label2.setBounds(420, 355, 70, 30);
        //数量输入框
        contentPane.add(textField2);
        textField2.setBounds(470, 355, 130, 30);


        button1.setText("修改");
        contentPane.add(button1);
        button1.setBounds(300, 355, 100, 30);


        button2.setText("加入购物车");
        contentPane.add(button2);
        button2.setBounds(610, 355, 100, 30);
        button2.addActionListener(
                (e) -> {
                    int rowNo = table1.getSelectedRow();//获取所选的行号
                    amount.add(textField2.getText());
                    listc.add(list.get(rowNo));
                }
        );

        button3.setText("进入购物车");
        contentPane.add(button3);
        button3.setBounds(710, 355, 100, 30);
        button3.addActionListener(
                (e)->{
                    ShoppingCart shoppingCart = new ShoppingCart(listc,amount);
                    shoppingCart.setVisible(true);
                }
        );
        //查询框
        contentPane.add(textField1);
        textField1.setBounds(140, 355, 130, 30);

        button4.setText("查询");
        contentPane.add(button4);
        button4.setBounds(20, 355, 100, 30);

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

        java.util.List<Item> list = new ArrayList<Item>();
        Connection conn = null;
        String user = "root";
        String dbPassword = "wyfnb666";
        String url = "jdbc:mysql://47.94.211.86:3306/shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        Statement stmt = null;
        String sql = "SELECT * FROM item";
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, dbPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getString(1));
                item.setTitle(rs.getString(2));
                item.setPrice(rs.getFloat(3));
                item.setDescription(rs.getString(4));
                item.setSales(rs.getInt(5));
                this.list.add(item);
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
            data[i][0] = this.list.get(i).getTitle();
            data[i][1] = this.list.get(i).getPrice();
            data[i][2] = this.list.get(i).getDescription();
            data[i][3] = this.list.get(i).getSales();
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"商品名称", "单价", "描述", "促销价"};
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
        new SellMain();
    }
}