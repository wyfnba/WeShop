package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.dao.IncreaseItemStockDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 21:05:26
 */
public class IncreaseItemImpl implements IncreaseItemStockDao {
    @Override
    public void increaseItemStock(Item_stock item_stock) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            String sql = "UPDATE item_stock SET stock = ? WHERE item_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,item_stock.getStock());
            pstmt.setString(2,item_stock.getItem_id());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("修改商品库存失败");
        }
    }
}
