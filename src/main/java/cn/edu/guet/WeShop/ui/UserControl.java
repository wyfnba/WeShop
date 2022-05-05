package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.ReturnOrderbase;
import cn.edu.guet.WeShop.bean.User;
import cn.edu.guet.WeShop.service.impl.UserServiceImpl;
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
public class UserControl extends JFrame {

    public UserControl() {
        initComponents();
    }
    private DefaultTableModel getDefaultTableModel() {
        return new DefaultTableModel(getDataFromDatabase(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        scrollPane2=new JScrollPane();
        table1 = new JTable();
        table2=new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 =new JButton();
        button5=new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();

        /*DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };*/

        DefaultTableModel tableModel = getDefaultTableModel();
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table1.setModel(tableModel);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        label1.setFont(new Font("STHeiti Light", Font.BOLD, 30));
        label1.setText("普通用户");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);

        label2.setText("用户姓名：");
        contentPane.add(label2);
        label2.setBounds(20, 355, 70, 30);

        contentPane.add(textField1);//搜索框
        textField1.setBounds(100, 355, 130, 30);

        button1.setText("查询");
        contentPane.add(button1);
        button1.setBounds(250, 355, 100, 30);
        button1.addActionListener(
                (e) -> {
                    this.setVisible(false);
                    String username=textField1.getText();
                    Search_User search_user=new Search_User(username);
                    search_user.setVisible(true);
                }
        );


        button2.setText("新增");
        contentPane.add(button2);
        button2.setBounds(500,355,100,30);
        button2.addActionListener(
                (e)->{
                    this.setVisible(false);
                    AddUser addUser=new AddUser();
                    addUser.setVisible(true);
                }
        );

        button3.setText("删除");
        contentPane.add(button3);
        button3.setBounds(600,355,100,30);
        button3.addActionListener(
                (e)->{
                    int rowNo=table1.getSelectedRow();
                    if(rowNo==-1){
                        this.setVisible(false);
                        Message message=new Message();
                        message.setVisible(true);
                    }else{
                        String userName=(String)table1.getValueAt(rowNo,2);
                        UserServiceImpl userService=new UserServiceImpl();
                        userService.deleteUser(userName);
                    }
                }
        );

        button4.setText("返回");
        contentPane.add(button4);
        button4.setBounds(700,355,100,30);
        button4.addActionListener(
                (e)->{
                    this.setVisible(false);
                    OrderList orderList=new OrderList();
                    orderList.setVisible(true);
                }
        );

        button5.setText("刷新");
        contentPane.add(button5);
        button5.setBounds(850,355,80,30);
        button5.addActionListener(
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


        scrollPane1.setViewportView(table1);
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
        java.util.List<User> list = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement ps=null;
        String sql = "SELECT u.username,u.password,u.status FROM user u";
        ResultSet rs = null;
        try {
            conn= ConnectionHandler.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                User user=new User();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setStatus(rs.getString(3));
                list.add(user);
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
        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getUsername();
            data[i][1] = list.get(i).getPassword();
            data[i][2] = list.get(i).getStatus();
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JTable table2;
    private String head[] = {"经手人姓名", "密码","职称"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;

}