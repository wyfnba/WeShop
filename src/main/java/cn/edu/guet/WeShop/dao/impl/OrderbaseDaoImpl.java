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
            String sql="INSERT INTO orderbase VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,orderbase.getId());
            pstmt.setInt(2,orderbase.getMch_id());
            pstmt.setString(3,orderbase.getOut_trade_no());
            pstmt.setTimestamp(4,orderbase.getTime_end());
            pstmt.setString(5,orderbase.getTransaction_id());
            pstmt.setString(6,orderbase.getUser_id());
            pstmt.setDouble(7,orderbase.getOrder_price());

            pstmt.executeUpdate();//真正执行sql语句

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("新增订单失败");
        }
    }
}
