package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.TableSearch.User_Return;
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
public class Search_Return extends JFrame {
    java.util.List<User_Return> list = new ArrayList<User_Return>();
    String username;
    public Search_Return(String username) {
        this.username=username;
        initComponents();
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();

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
        label1.setText("查询结果");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);

        button1.setText("返回");
        contentPane.add(button1);
        button1.setBounds(450,355,100,30);
        button1.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    Sale_Return sale_return=new Sale_Return();
                    sale_return.setVisible(true);
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
        PreparedStatement ps=null;
        String sql = "SELECT u.username,r.money,r.time \n" +
                     "FROM user u,return_orderbase r \n" +
                     "WHERE u.id=r.user_id and u.username='"+username+"'";
        ResultSet rs = null;
        try {
            conn= ConnectionHandler.getConn();
            ps = conn.prepareStatement(sql);
            //ps.setString(1,username);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                User_Return username_return=new User_Return();
                username_return.setUsername(rs.getString(1));
                username_return.setMoney(rs.getDouble(2));
                username_return.setTime(rs.getTimestamp(3));
                this.list.add(username_return);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } /*finally {
            try {
                rs.close();
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
    private String head[] = {"经手人姓名", "入账金额", "交易时间"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;
}