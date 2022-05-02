package cn.edu.guet.WeShop.dao;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 20:44:26
 */

import cn.edu.guet.WeShop.bean.Item;

import java.sql.SQLException;

/**
 * 进货时如果没有该商品就增加
 */
public interface AddItemDao {
    void AddItem(Item item) throws SQLException;
}
