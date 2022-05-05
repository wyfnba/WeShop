package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.dao.ItemDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 21:05:12
 */
public class ItemImpl implements ItemDao {
    @Override
    public void AddItem(List<Item> item) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();

            String sql = "INSERT INTO item(id,title,price,sales) VALUES(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            for(int i = 0 ; i<item.size() ; i++){

                pstmt.setString(1,item.get(i).getId());
                System.out.println("item:"+item.get(i).getId());
                pstmt.setString(2,item.get(i).getTitle());
                pstmt.setDouble(3,item.get(i).getPrice());
                pstmt.setInt(4,item.get(i).getSales());

                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增商品到商品表失败");
        }
    }
}
