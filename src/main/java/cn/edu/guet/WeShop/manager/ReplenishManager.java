package cn.edu.guet.WeShop.manager;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.service.IncomingOrderService;
import cn.edu.guet.WeShop.service.impl.IncomingOrderServiceImpl;

import java.util.UUID;

/**
 * @Author liwei
 * @Date 2021-08-04 11:28
 * @Version 1.0
 */
public class ReplenishManager {
    boolean flag;
    double price = 0;
    String title;

    public ReplenishManager(boolean flag){
        this.flag = flag;
    }

    public ReplenishManager(boolean flag,String title){
        this.title = title;
        this.flag = flag;
    }


    public void PackagingClass(double money,String user_id,String item_id,double amount,double stock){


        IncomingOrderbase incomingOrderbase = new IncomingOrderbase();
        String id = UUID.randomUUID().toString().replace("-", "");
        incomingOrderbase.setId(id);//会自动生成32个字符
        incomingOrderbase.setUser_id(user_id);
        incomingOrderbase.setMoney(money);

        IncomingOrderdetail incomingOrderdetail = new IncomingOrderdetail();
        incomingOrderdetail.setIncoming_orderbase_id(id);
        incomingOrderdetail.setItem_id(item_id);
        incomingOrderdetail.setAmount(amount);

        Item_stock item_stock = new Item_stock();
        item_stock.setStock(stock);
        item_stock.setItem_id(item_id);


        IncomingOrderService replenishStockService = new IncomingOrderServiceImpl();
        if (flag){
            Item item = new Item();
            item.setTitle(title);
            item.setPrice(price);
            item.setSales(0);

            String id1 = UUID.randomUUID().toString().replace("-", "");
            item.setId(id1);
            incomingOrderdetail.setItem_id(id1);
            item_stock.setItem_id(id1);

            replenishStockService.newIncomingOrderCaseTwo(incomingOrderdetail,incomingOrderbase,item_stock,item);
        }else{
            replenishStockService.newIncomingOrderCaseOne(incomingOrderdetail,incomingOrderbase,item_stock);
        }

    }
}