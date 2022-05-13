package cn.edu.guet.WeShop.manager;

import cn.edu.guet.WeShop.bean.*;
import cn.edu.guet.WeShop.dao.ReturnOrderDetailDao;
import cn.edu.guet.WeShop.dao.impl.ReturnOrderDetailImpl;
import cn.edu.guet.WeShop.service.ReturnOrderService;
import cn.edu.guet.WeShop.service.impl.ReturnOrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 11:24:09
 */
public class ReturnsManager {
    String user_id;

    public ReturnsManager(String user_id) {
        this.user_id = user_id;
    }

    public void PackingClass(List<String> item_ids, List<Double> stocks, List<Double> amounts, double money) {
        ReturnOrderbase returnOrderbase = new ReturnOrderbase();
        String id = UUID.randomUUID().toString().replace("-", "");
        returnOrderbase.setId(id);//会自动生成32个字符
        returnOrderbase.setUser_id(user_id);
        returnOrderbase.setMoney(money);

        ReturnOrderService returnOrderService = new ReturnOrderServiceImpl(returnOrderbase);
        List<ReturnOrderdetail> returnOrderDetails = new ArrayList<>();
        List<Item_stock> item_stocks = new ArrayList<>();

        for (int i = 0 ; i < item_ids.size() ; i++){
            ReturnOrderdetail returnOrderDetail = new ReturnOrderdetail();
            returnOrderDetail.setReturn_orderbase_id(id);
            returnOrderDetail.setItem_id(item_ids.get(i));
            returnOrderDetail.setAmount(amounts.get(i));

            Item_stock item_stock = new Item_stock();
            item_stock.setStock(amounts.get(i));
            item_stock.setItem_id(item_ids.get(i));

            returnOrderDetails.add(returnOrderDetail);
            item_stocks.add(item_stock);
        }

        returnOrderService.newReturnOrder(returnOrderDetails,item_stocks);

    }
}
