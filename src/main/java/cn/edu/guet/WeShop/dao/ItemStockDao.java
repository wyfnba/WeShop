package cn.edu.guet.WeShop.dao;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 20:44:47
 */

import cn.edu.guet.WeShop.bean.Item_stock;

import java.sql.SQLException;
import java.util.List;

/**
 * 进货时增加库存
 */
public interface ItemStockDao {
    void increaseItemStock(List<Item_stock> item_stock) throws SQLException;
}
