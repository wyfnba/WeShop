package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.ReturnOrderdetail;

import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 13:06:01
 */
public interface ReturnOrderDetailDao {
    void addOrderDetail(ReturnOrderdetail returnOrderDetail) throws SQLException;
}
