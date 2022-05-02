package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.Item;

import javax.swing.*;
import java.awt.*;

/**
 * @liwei
 */
public class Pay extends JFrame {

    public Pay() {
        initComponents();
    }

    private void initComponents() {
        /*label1 = new JLabel();
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
        */

        //======== this ========
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        ImageIcon imageIconSource = new ImageIcon("E:\\idea-projects\\WeShop\\src\\main\\resources\\new.jpg");
        Image image = imageIconSource.getImage();
        Image tempImage = image.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(tempImage);
        JLabel photoView = new JLabel();
        photoView.setIcon(imageIcon);

        //---- label1 ----
        contentPane.add(photoView);
        photoView.setBounds(130, 80, 200, 200);


/*
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
/*
        //---- button1 ----
        button1.setText("保存");
        contentPane.add(button1);
        button1.setBounds(200, 300, 100, 30);
        button1.addActionListener(
                (e)->{
                    System.out.println("准备保存");
                    // 执行UPDATE
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

 */

        pack();
        setLocationRelativeTo(getOwner());
        this.setBounds(600, 300, 480, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JLabel photoView;
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
