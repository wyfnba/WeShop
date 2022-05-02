package cn.edu.guet.WeShop.service;

import cn.edu.guet.WeShop.bean.Orderbase;
import cn.edu.guet.WeShop.bean.Orderdetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void addOrder(Orderbase orderbase, List<Orderdetail> orderdetailList)throws SQLException;
}
