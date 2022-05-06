package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.dao.ItemDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 21:05:12
 */
public class ItemImpl implements ItemDao {
    @Override
    public void AddItem(Item item) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            String sql = "INSERT INTO item(id,title,price,sales) VALUES(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,item.getId());
            pstmt.setString(2,item.getTitle());
            pstmt.setDouble(3,item.getPrice());
            pstmt.setInt(4,item.getSales());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增商品到商品表失败");
        }
    }
}
