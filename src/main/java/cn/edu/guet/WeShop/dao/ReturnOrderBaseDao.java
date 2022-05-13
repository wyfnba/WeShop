package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.ReturnOrderbase;

import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 13:05:47
 */
public interface ReturnOrderBaseDao {
    void addOrderBase(ReturnOrderbase returnOrderBase) throws SQLException;
}
