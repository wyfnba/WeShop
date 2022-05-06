package cn.edu.guet.WeShop.service.impl;

import cn.edu.guet.WeShop.bean.User;
import cn.edu.guet.WeShop.dao.Userdao;
import cn.edu.guet.WeShop.dao.impl.UserdaoImpl;
import cn.edu.guet.WeShop.service.UserService;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;


public class UserServiceImpl implements UserService {
    Connection conn=null;
    public void addUser(User user){
        try {
            Userdao addUser=new UserdaoImpl();

            conn=ConnectionHandler.getConn();

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
    public void deleteUser(String username) {
        try {
            Userdao deleteUser=new UserdaoImpl();
            conn=ConnectionHandler.getConn();

            conn.setAutoCommit(false);
            deleteUser.DeleteUser(username);
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
