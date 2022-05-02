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
    double price;
    double stock;
    String title;

    public ReplenishManager(boolean flag,double stock){
        this.flag = flag;
        this.stock = stock;
    }

    public ReplenishManager(boolean flag,double stock,double price,String title){
        this.title = title;
        this.flag = flag;
        this.price = price;
        this.stock = stock;
    }


    public void PackagingClass(double money,String user_id,String item_id,double amount){
        /*
                Order order = new Order();
                String id = UUID.randomUUID().toString().replace("-", "");
                System.out.println(id);
                order.setId(id);//会自动生成32个字符
                order.setMch_id(Integer.parseInt(mch_id));
                order.setOut_trade_no(orderNo);
                order.setOrder_time(timestamp);
                order.setTransaction_id(transactionId);
                order.setUserId(0);
                order.setItem_id(11111);
                order.setItem_price(18.00f);
                order.setAmount(1);
                order.setOrder_price(18.00f);

                OrderService orderService = new OrderServiceImpl();

                orderService.newOrder(order, item_id, item_amount);
                */

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
            replenishStockService.newIncomingOrderCaseTwo(incomingOrderdetail,incomingOrderbase,item_stock,item);
        }else{
            replenishStockService.newIncomingOrderCaseOne(incomingOrderdetail,incomingOrderbase,item_stock);
        }

    }
}
