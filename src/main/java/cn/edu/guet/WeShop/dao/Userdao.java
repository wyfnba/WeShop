package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.User;

import java.sql.SQLException;

public interface Userdao {
    void AddUser(User user) throws SQLException;//增加用户
    void DeleteUser(String userName) throws SQLException;//删除用户
}
