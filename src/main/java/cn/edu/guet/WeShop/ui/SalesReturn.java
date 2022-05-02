package cn.edu.guet.WeShop.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @liwei
 */
public class SalesReturn extends JFrame {
    //Item item;
    String title;
    int stock;

    public SalesReturn() {
        //this.item = item;
        initComponents();
    }

    public SalesReturn(String title, int stock) {
        //this.item = item;
        this.title = title;
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

        /*//---- label2 ----
        label2.setText("剩余库存");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        textField2.setText(String.valueOf(stock));*/

        //---- label3 ----
        label3.setText("退货量");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);
        //textField3.setText(String.valueOf(item.getPrice()));

        //---- label4 ----
        label4.setText("退货总价");
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
        button1.setText("生成退货单");
        contentPane.add(button1);
        button1.setBounds(200, 300, 100, 30);
        button1.addActionListener(
                (e)->{
                    System.out.println("准备保存");
                    // 执行UPDATE
                    this.setVisible(false);
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
