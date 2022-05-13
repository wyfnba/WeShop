package cn.edu.guet.WeShop.ui;

import javax.swing.*;
import java.awt.*;

public class Message extends JFrame {
    public Message(){
        initComponents();
    }

    public void initComponents(){
        Label1=new JLabel();
        button1=new JButton();

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        Label1.setText("您没有选中要删除的项目！");
        contentPane.add(Label1);
        Label1.setBounds(75,30,200,30);

        button1.setText("确定");
        contentPane.add(button1);
        button1.setBounds(100,80,100,30);
        button1.addActionListener(
                (e)->{
                    this.setVisible(false);
                }
        );

        pack();
        setLocationRelativeTo(getOwner());
        this.setBounds(750, 500, 300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton button1;
    private JLabel Label1;
}
