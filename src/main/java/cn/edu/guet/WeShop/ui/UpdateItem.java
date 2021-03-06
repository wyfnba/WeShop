package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.util.ConnectionHandler;
import org.springframework.context.annotation.Description;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @liwei
 */
public class UpdateItem extends JFrame {
    Item item;
    SellMain sellMain = new SellMain();
    java.util.List<Item> list = new ArrayList<Item>();
    int rowNo;

    public UpdateItem(Item item,SellMain sellMain,java.util.List<Item> list,int rowNo) {
        this.item = item;
        this.sellMain = sellMain;
        this.list = list;
        this.rowNo = rowNo;
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

        //======== this ========
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5546\u54c1\u540d\u79f0\uff1a");
        contentPane.add(label1);
        label1.setBounds(20, 20, 65, 20);
        contentPane.add(textField1);
        textField1.setBounds(75, 20, 130, 20);
        textField1.setText(item.getTitle());

        //---- label2 ----
        label2.setText("\u5355\u4ef7\uff1a");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        textField2.setText(String.valueOf(item.getPrice()));

        //---- label3 ----
        label3.setText("\u63cf\u8ff0\uff1a");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(75, 80, 130, 20);
        textField3.setText(item.getDescription());

        //---- label4 ----
        label4.setText("\u4fc3\u9500\u4ef7\uff1a");
        contentPane.add(label4);
        label4.setBounds(240, 80, 90, 20);
        contentPane.add(textField4);
        textField4.setBounds(300, 80, 130, 20);
        textField4.setText(String.valueOf(item.getSales()));

        /*
        //---- label5 ----
        label5.setText("\u4fc3\u9500\u4ef7\uff1a");
        contentPane.add(label5);
        label5.setBounds(20, 140, 55, 20);
        contentPane.add(textField5);
        textField5.setBounds(70, 140, 130, 20);
        textField5.setText(String.valueOf(item.getSales()));

         */

        /*
        //---- label6 ----
        label6.setText("\u5546\u54c1\u56fe\u7247\uff1a");
        contentPane.add(label6);
        label6.setBounds(240, 140, 90, 20);
        contentPane.add(textField6);
        textField6.setBounds(300, 140, 130, 20);
        textField6.setText(item.getImg_url());

         */

        //---- button1 ----
        button1.setText("??????");
        contentPane.add(button1);
        button1.setBounds(200, 300, 100, 30);
        button1.addActionListener(
                (e)->{
                    System.out.println("????????????");
                    Item item = new Item();
                    item.setTitle(textField1.getText());
                    item.setPrice(Double.parseDouble(textField2.getText()));
                    item.setDescription(textField3.getText());
                    item.setSales(Integer.parseInt(textField4.getText()));
                    //System.out.println((textField3.getText()));
                    list.set(rowNo,item);
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    //String user = "root";
                    //String dbPassword = "wyfnb666";
                    //String url = "jdbc:mysql://47.94.211.86:3306/shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                    //ResultSet rs = null;
                    try {
                        conn = ConnectionHandler.getConn();

                        String sql= "UPDATE item SET price = ?,description = ?,sales = ? WHERE title = ?";
                        stmt =conn.prepareStatement(sql);
                        stmt.setDouble(1,Double.parseDouble(textField2.getText()));
                        stmt.setString(2,textField3.getText());
                        stmt.setInt(3, Integer.parseInt(textField4.getText()));
                        stmt.setString(4,textField1.getText());
                        stmt.executeUpdate();
                        //rs = stmt.executeQuery();

                        //while (rs.next()) {
                            //Item itemupdate = new Item();
                            /*PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1,textField1.getText());
                            pstmt.setDouble(2,Double.parseDouble(textField2.getText()));
                            pstmt.setString(3,textField3.getText());
                            pstmt.setDouble(4,Double.parseDouble(textField4.getText()));
                            pstmt.executeUpdate();*/
                            //list.add(itemupdate);
                        //}

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    this.setVisible(false);
                    this.sellMain.setVisible(false);

                    SellMain sel = new SellMain(list);
                    sel.setVisible(true);


                    //item.setPrice(textField2.getPrice());
                    //item.setDescription(textField3.getDescription());
                    //item.setSales(textField4.getSales());

                    //UpdateItem updateItem=new UpdateItem(item);






                    // ??????UPDATE
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
}





