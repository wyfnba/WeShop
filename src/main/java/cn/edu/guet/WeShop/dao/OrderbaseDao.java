package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.Orderbase;

import java.sql.SQLException;

public interface OrderbaseDao {

    void addOrder(Orderbase orderbase) throws SQLException;
}
