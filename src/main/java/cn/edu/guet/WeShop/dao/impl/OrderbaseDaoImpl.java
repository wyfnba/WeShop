package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.Orderbase;
import cn.edu.guet.WeShop.dao.OrderbaseDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderbaseDaoImpl implements OrderbaseDao {


    @Override
    public void addOrder(Orderbase orderbase) throws SQLException {
        Connection conn=null;
        try {
            conn= ConnectionHandler.getConn();
            System.out.println("OrderbaseDaoImpl："+conn.hashCode());
            String sql="INSERT INTO orderbase(id,mch_id,out_trade_no,transaction_id,user_id,order_price) VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,orderbase.getId());
            pstmt.setInt(2,orderbase.getMch_id());
            pstmt.setString(3,orderbase.getOut_trade_no());
            pstmt.setString(4,orderbase.getTransaction_id());
            pstmt.setString(5,orderbase.getUser_id());
            pstmt.setDouble(6,orderbase.getOrder_price());

            pstmt.executeUpdate();//真正执行sql语句

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("新增订单失败");
        }
    }
}
