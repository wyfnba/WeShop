package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.TableSearch.SumMoney;
import cn.edu.guet.WeShop.TableSearch.User_Incoming;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @liwei
 */
public class Sale_Stock extends JFrame {
    java.util.List<User_Incoming> list = new ArrayList<User_Incoming>();
    public Sale_Stock() {
        initComponents();
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2=new JButton();
        label1 = new JLabel();
        label2 =new JLabel();
        label3 =new JLabel();
        label4=new JLabel();

        textField1=new JTextField("pje");

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
        label1.setText("进货表");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);


        label2.setText("经手人姓名：");
        contentPane.add(label2);
        label2.setBounds(20, 355, 100, 30);
        contentPane.add(textField1);
        textField1.setBounds(100, 355, 130, 30);

        label3.setFont(new Font("宋体",Font.BOLD,15));
        label3.setText("出账金额：");
        contentPane.add(label3);
        label3.setBounds(400,355,80,30);

        Double money;
        SumMoney sumMoney=new SumMoney();
        money=sumMoney.IncomingMoney();
        label4.setText(String.valueOf(money));
        contentPane.add(label4);
        label4.setBounds(500,355,100,30);

        button1.setText("查询");
        contentPane.add(button1);
        button1.setBounds(250, 355, 100, 30);
        button1.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    String username=textField1.getText();
                    Search_Incoming incoming_search=new Search_Incoming(username);
                    incoming_search.setVisible(true);
                }
        );


        button2.setText("返回");
        contentPane.add(button2);
        button2.setBounds(800,355,100,30);
        button2.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    OrderList orderList=new OrderList();
                    orderList.setVisible(true);
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

        Connection conn = null;
        ResultSet rs = null;
        String sql = "SELECT u.username,i.money,i.time\n" +
                "FROM user u,incoming_orderbase i\n" +
                "WHERE u.id=i.user_id \n" +
                "GROUP BY i.id";
        PreparedStatement ps=null;
        try {
            conn= ConnectionHandler.getConn();
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                User_Incoming username_incoming=new User_Incoming();
                username_incoming.setUsername(rs.getString(1));
                username_incoming.setMoney(rs.getDouble(2));
                username_incoming.setTime(rs.getTimestamp(3));
                this.list.add(username_incoming);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(1);
        } /*finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
        // 把集合的数据（商品信息）转换成二维数组
        data = new Object[this.list.size()][head.length];

        for (int i = 0; i < this.list.size(); i++) {
            data[i][0] = this.list.get(i).getUsername();
            data[i][1] = this.list.get(i).getMoney();
            data[i][2] = this.list.get(i).getTime();

        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"经手人姓名", "出账金额","交易时间"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
}