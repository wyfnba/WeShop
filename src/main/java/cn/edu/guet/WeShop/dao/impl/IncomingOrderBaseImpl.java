package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.dao.IncomingOrderBaseDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 17:57:44
 */
public class IncomingOrderBaseImpl implements IncomingOrderBaseDao {
    @Override
    public void addOrderBase(IncomingOrderbase incomingOrderbase) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            String sql = "INSERT INTO incoming_orderbase(id,user_id,money) VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,incomingOrderbase.getId());
            pstmt.setString(2,incomingOrderbase.getUser_id());
            pstmt.setDouble(3,incomingOrderbase.getMoney());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增进货订单到基本表失败");
        }
    }
}
