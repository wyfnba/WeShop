package cn.edu.guet.WeShop.dao.impl;

import cn.edu.guet.WeShop.bean.User;
import cn.edu.guet.WeShop.dao.Userdao;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserdaoImpl implements Userdao {

    @Override
    public void AddUser(User user) throws SQLException{
        Connection conn=null;
        try {
            conn= ConnectionHandler.getConn();
            String sql="INSERT INTO user VALUES(?,?,?,?)";

            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,null);//id
            pstmt.setString(2,user.getUsername());
            pstmt.setString(3,user.getPassword());
            pstmt.setString(4,user.getStatus());

            pstmt.executeUpdate();//真正执行sql语句

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("新增用户失败");
        }
    }

    @Override
    public void DeleteUser(String userName) throws SQLException{
        Connection conn=null;
        try {
            conn= ConnectionHandler.getConn();
            String sql="DELETE FROM user WHERE username=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,userName);

            pstmt.executeUpdate();//真正执行sql语句

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("删除用户失败");
        }
    }
}
