package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.dao.IncomingOrderDetailDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 17:52:19
 */
public class IncomingOrderDetailImpl implements IncomingOrderDetailDao {

    @Override
    public void addOrderDetail(List<IncomingOrderdetail> incomingOrderDetail) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionHandler.getConn();

            String sql = "INSERT INTO incoming_orderdetail(incoming_orderbase_id,item_id,amount) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0 ; i < incomingOrderDetail.size() ; i++){

                pstmt.setString(1,incomingOrderDetail.get(i).getIncoming_orderbase_id());
                pstmt.setString(2,incomingOrderDetail.get(i).getItem_id());
                pstmt.setDouble(3,incomingOrderDetail.get(i).getAmount());

                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("新增进货订单到细节表失败");
        }
    }
}
