package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.dao.ItemStockDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 21:05:26
 */
public class ItemStockImpl implements ItemStockDao {
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

    @Override
    public void insertItemStock(Item_stock item_stock) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            String sql = "INSERT INTO item_stock VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,item_stock.getId());
            pstmt.setDouble(2,item_stock.getStock());
            pstmt.setString(3,item_stock.getItem_id());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增商品库存记录失败");
        }
    }

    @Override
    public void decreaseItemStock(Item_stock item_stock) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            System.out.println("ItemStockDaoImpl："+conn.hashCode());
            String sql = "UPDATE item_stock SET stock = stock-? WHERE item_id = ?";

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
