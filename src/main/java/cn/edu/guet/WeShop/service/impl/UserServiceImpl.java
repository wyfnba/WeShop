package cn.edu.guet.WeShop.service.impl;

import cn.edu.guet.WeShop.bean.User;
import cn.edu.guet.WeShop.dao.Userdao;
import cn.edu.guet.WeShop.dao.impl.UserdaoImpl;
import cn.edu.guet.WeShop.service.UserService;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;


public class UserServiceImpl implements UserService {

    public void addUser(User user){
        Connection conn=null;
        try {
            Userdao addUser=new UserdaoImpl();
            conn= ConnectionHandler.getConn();

            conn.setAutoCommit(false);
            addUser.AddUser(user);
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("事务回滚");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                ConnectionHandler.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        Connection conn=null;
        try {
            Userdao deleteUser=new UserdaoImpl();
            conn= ConnectionHandler.getConn();

            conn.setAutoCommit(false);
            deleteUser.DeleteUser(user.getUsername());
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("事务回滚");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                ConnectionHandler.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
