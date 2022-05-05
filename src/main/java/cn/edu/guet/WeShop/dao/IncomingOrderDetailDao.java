package cn.edu.guet.WeShop.dao;

import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Orderdetail;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 17:48:59
 */

/**
 * 这是进货时对细节表的更新接口
 */
public interface IncomingOrderDetailDao {
    void addOrderDetail(List<IncomingOrderdetail> incomingOrderDetail) throws SQLException;
}
