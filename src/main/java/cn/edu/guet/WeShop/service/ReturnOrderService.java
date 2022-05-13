package cn.edu.guet.WeShop.service;

import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.bean.ReturnOrderdetail;

import java.util.List;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 13:11:57
 */
public interface ReturnOrderService {
    void newReturnOrder(List<ReturnOrderdetail> returnOrderDetails,List<Item_stock> item_stocks);
}
