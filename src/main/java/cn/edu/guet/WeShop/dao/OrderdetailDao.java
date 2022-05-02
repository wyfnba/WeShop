package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.Orderdetail;

import java.sql.SQLException;

public interface OrderdetailDao {
    void addOrder(Orderdetail orderdetail) throws SQLException;
}
