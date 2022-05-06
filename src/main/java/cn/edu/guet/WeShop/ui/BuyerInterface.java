package cn.edu.guet.WeShop.ui;

import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @liwei
 */
public class BuyerInterface extends JFrame {
    String user_id;
    public BuyerInterface(String user_id) {
        this.user_id = user_id;
        initComponents();
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label1 = new JLabel();
        textField1 = new JTextField();

        DefaultTableModel tableModel = getDefaultTableModel();
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table1.setModel(tableModel);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        label1.setFont(new
                Font("STHeiti Light", Font.BOLD,
                30));
        label1.setText("已有商品");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);

        /*button1.setText("退货");
        contentPane.add(button1);
        button1.setBounds(510, 355, 100, 30);
        button1.addActionListener(
                (e)->{
                    int rowNo = table1.getSelectedRow();//获取所选的行号
                    if (rowNo != -1){
                        String title = (String) table1.getValueAt(rowNo,0);
                        int stock = (int) table1.getValueAt(rowNo,1);
                        SalesReturn sr = new SalesReturn(title,stock);
                        sr.setVisible(true);
                    }else{
                        SalesReturn sr = new SalesReturn();
                        sr.setVisible(true);
                    }
                }
        );*/

        button2.setText("进货");
        contentPane.add(button2);
        button2.setBounds(610, 355, 100, 30);
        button2.addActionListener(
                (e)->{
                    List<String> list = randomNumber();
                    int rowNo = table1.getSelectedRow();//获取所选的行号
                    if (rowNo != -1){
                        String title = (String) table1.getValueAt(rowNo,0);
                        ReplenishStock rs = new ReplenishStock(title,user_id,list);//list表示本次进货最多只能进50个商品
                        rs.setVisible(true);
                    }else{
                        ReplenishStock rs = new ReplenishStock(user_id,list);
                        rs.setVisible(true);
                    }
                }
        );

        button3.setText("刷新");
        contentPane.add(button3);
        button3.setBounds(710, 355, 100, 30);
        button3.addActionListener(
                (e)->{
                    table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    table1.setModel(getDefaultTableModel());
                }
        );


        /*button3.setText("修改");
        contentPane.add(button3);
        button3.setBounds(710, 355, 100, 30);
        button3.addActionListener(
                (e)->{
                    int rowNo = table1.getSelectedRow();//获取所选的行号
                    int id=(int)table1.getValueAt(rowNo, 0);
                    String title=(String)table1.getValueAt(rowNo, 1);
                    Float price=(Float)table1.getValueAt(rowNo, 2);
                    String description=(String)table1.getValueAt(rowNo, 3);
                    int sales=(int)table1.getValueAt(rowNo, 4);
                    String img_url=(String)table1.getValueAt(rowNo, 5);
                    System.out.println(id);
                    System.out.println(title);
                    System.out.println(price);
                    System.out.println(description);
                    System.out.println(sales);
                    System.out.println(img_url);

                    Item item=new Item(id,title,price,description,sales,img_url);

                    UpdateItem updateItem=new UpdateItem(item);
                    updateItem.setVisible(true);
                }
        );*/

        contentPane.add(textField1);
        textField1.setBounds(270, 355, 130, 30);

        button4.setText("查询");
        contentPane.add(button4);
        button4.setBounds(410, 355, 100, 30);
        button4.addActionListener(
                (e)->{
                    String title = textField1.getText();
                    String s = " AND title='" + title + "'";

                    //执行并显示
                    DefaultTableModel tableModel1 = new DefaultTableModel(getDataFromDatabase(s), head) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    table1.setModel(tableModel1);
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
        this.setBounds(300, 300, 1000, 415);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private DefaultTableModel getDefaultTableModel() {
        return new DefaultTableModel(getDataFromDatabase(""), head) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
    }

    //随机生成50个不重复的数字
    private List<String> randomNumber(){
        List<String> list = new ArrayList<>();
        String number;
        for(int i = 0 ; i < 50 ; i++){
            while(true){
                number = String.valueOf((int)(Math.random()*76));
                if(!list.contains(number)){
                    //如果不包含该值
                    list.add(number);
                    break;
                }
            }
        }
        return list;
    }

    public Object[][] getDataFromDatabase(String s) {

        List<Item> list = new ArrayList<>();
        List<Item_stock> list2 = new ArrayList<>();
        Connection conn = null;
        String user = "root";
        String dbPassword = "wyfnb666";
        String url = "jdbc:mysql://47.94.211.86:3306/shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        Statement stmt = null;
        String sql = "SELECT title,stock FROM item a,item_stock b WHERE a.id = b.item_id"+s+";";
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, dbPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Item item = new Item();
                item.setTitle(rs.getString(1));
                Item_stock item_stock = new Item_stock();
                item_stock.setStock(rs.getInt(2));
                list.add(item);
                list2.add(item_stock);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (rs != null)rs.close();
                if (stmt != null)stmt.close();
                if (conn != null)conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        // 把集合的数据（商品信息）转换成二维数组
        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getTitle();
                data[i][1] = list2.get(i).getStock();
            }
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"商品名称", "剩余数量"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JLabel label1;
}