package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.User;
import cn.edu.guet.WeShop.service.UserService;
import cn.edu.guet.WeShop.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @liwei
 */
public class AddUser extends JFrame {

    public AddUser() {
        initComponents();
    }

    private void initComponents() {
        label1 = new JLabel();
        textField1 = new JTextField("test");
        label2 = new JLabel();
        textField2 = new JTextField("test");
        label3 = new JLabel();
        textField3 = new JTextField("test");
        label4 = new JLabel();
        textField4 = new JTextField();
        button1 = new JButton();
        button2 =new JButton();

        //======== this ========
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("用户名");
        contentPane.add(label1);
        label1.setBounds(20, 20, 55, 20);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 130, 20);
        //textField1.setText(user.getUsername());

        //---- label2 ----
        label2.setText("密码");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        //textField2.setText(user.getPassword());

        //---- label3 ----
        label3.setText("职位");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);
        //textField3.setText(user.getStatus());


        //---- button1 ----
        button1.setText("保存");
        contentPane.add(button1);
        button1.setBounds(200, 300, 100, 30);
        button1.addActionListener(
                (e)->{
                    User user=new User();
                    user.setUsername(textField1.getText());
                    user.setPassword(textField2.getText());
                    user.setStatus(textField3.getText());

                    UserService userService=new UserServiceImpl();
                    userService.addUser(user);

                    this.setVisible(false);
                    UserControl userControl=new UserControl();
                    userControl.setVisible(true);
                }
        );

        button2.setText("返回");
        contentPane.add(button2);
        button2.setBounds(350, 300, 100, 30);
        button2.addActionListener(
                (e)->{
                    this.setVisible(false);
                    UserControl userControl=new UserControl();
                    userControl.setVisible(true);
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
    private JButton button2;
}
