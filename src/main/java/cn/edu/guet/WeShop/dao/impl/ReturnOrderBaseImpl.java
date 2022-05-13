package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.ReturnOrderbase;
import cn.edu.guet.WeShop.dao.ReturnOrderBaseDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 13:12:48
 */
public class ReturnOrderBaseImpl implements ReturnOrderBaseDao {
    @Override
    public void addOrderBase(ReturnOrderbase returnOrderBase) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            String sql = "INSERT INTO return_orderbase(id,user_id,money) VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,returnOrderBase.getId());
            pstmt.setString(2,returnOrderBase.getUser_id());
            pstmt.setDouble(3,returnOrderBase.getMoney());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增进货订单到基本表失败");
        }
    }
}
