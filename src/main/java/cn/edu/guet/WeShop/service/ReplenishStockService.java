package cn.edu.guet.WeShop.service;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 19:44:05
 */
public interface ReplenishStockService {

    //第一种情况是：商品表已存在进货的商品
    void newIncomingOrderCaseOne(IncomingOrderdetail incomingOrderdetail, IncomingOrderbase incomingOrderbase, Item_stock item_stock);

    //第二种情况是：商品表还没有进货的商品
    void newIncomingOrderCaseTwo(IncomingOrderdetail incomingOrderdetail, IncomingOrderbase incomingOrderbase, Item_stock item_stock, Item item);
}
