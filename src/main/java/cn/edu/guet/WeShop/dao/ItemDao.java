package cn.edu.guet.WeShop.dao;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 20:44:26
 */

import cn.edu.guet.WeShop.bean.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * 进货时如果没有该商品就增加
 */
public interface ItemDao {
    void AddItem(List<Item> item) throws SQLException;
}
