package cn.edu.guet.WeShop.TableSearch;

import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


public class SumMoney {
    public Double IncomingMoney(){
        Double money=0.0;

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="SELECT SUM(money) FROM incoming_orderbase";
        try {
            conn=ConnectionHandler.getConn();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while (rs.next()){
                money=rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }

    public Double ReturnMoney(){
        Double money=0.0;

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="SELECT SUM(money) FROM return_orderbase";
        try {
            conn=ConnectionHandler.getConn();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while (rs.next()){
                money=rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }
}
