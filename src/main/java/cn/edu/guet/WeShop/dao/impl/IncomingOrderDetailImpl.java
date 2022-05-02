package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.dao.IncomingOrderDetailDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 17:52:19
 */
public class IncomingOrderDetailImpl implements IncomingOrderDetailDao {

    @Override
    public void addOrderDetail(IncomingOrderdetail incomingOrderdetail) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();
            String sql = "INSERT INTO incoming_orderdetail(incoming_orderbase_id,item_id,amount) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,incomingOrderdetail.getIncoming_orderbase_id());
            pstmt.setString(2,incomingOrderdetail.getItem_id());
            pstmt.setDouble(3,incomingOrderdetail.getAmount());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增进货订单到细节表失败");
        }
    }
}
