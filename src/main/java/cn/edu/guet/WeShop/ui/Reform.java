/*
 * Created by JFormDesigner on Fri May 13 11:40:05 CST 2022
 */

package cn.edu.guet.WeShop.ui;

import java.awt.*;
import javax.swing.*;

/**
 * @author 1
 */
public class Reform extends JFrame {
    String text;
    public Reform() {
        initComponents();
    }

    public Reform(String text) {
        this.text = text;
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("error");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(text);
        contentPane.add(label1);
        label1.setBounds(30, 95, 335, 50);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(165, 190), button1.getPreferredSize()));
        button1.addActionListener(
                (e)->{
                    this.setVisible(false);
                }
        );

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
