package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.Orderdetail;
import cn.edu.guet.WeShop.dao.OrderdetailDao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderdetailDaoImpl implements OrderdetailDao {

    @Override
    public void addOrder(Orderdetail orderdetail) throws SQLException {
        Connection conn=null;
        try {
            conn= ConnectionHandler.getConn();
            System.out.println("OrderDaoImpl："+conn.hashCode());
            String sql="INSERT INTO orderdetail VALUES(?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,orderdetail.getId());
            pstmt.setString(2,orderdetail.getOrderbase_id());
            pstmt.setString(3,orderdetail.getItem_id());
            pstmt.setDouble(4,orderdetail.getAmount());

            pstmt.executeUpdate();//真正执行sql语句

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("新增订单失败");
        }
    }
}
