package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.Orderbase;

import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 17:48:59
 */

/**
 * 这是进货时对基础表的更新接口
 */
public interface ReplenishStockBaseDao {
    void addOrderBase(IncomingOrderbase incomingOrderbase) throws SQLException;
}
