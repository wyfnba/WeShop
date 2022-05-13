package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.ReturnOrderdetail;
import cn.edu.guet.WeShop.dao.ReturnOrderDetailDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 13:13:22
 */
public class ReturnOrderDetailImpl implements ReturnOrderDetailDao {
    @Override
    public void addOrderDetail(ReturnOrderdetail returnOrderDetail) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();

            String sql = "INSERT INTO return_orderdetail(return_orderbase_id,item_id,amount) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, returnOrderDetail.getReturn_orderbase_id());
            pstmt.setString(2, returnOrderDetail.getItem_id());
            pstmt.setDouble(3, returnOrderDetail.getAmount());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增进货订单到细节表失败");
        }
    }
}
