package cn.edu.guet.WeShop.manager;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.service.IncomingOrderService;
import cn.edu.guet.WeShop.service.impl.IncomingOrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author liwei
 * @Date 2021-08-04 11:28
 * @Version 1.0
 */
public class ReplenishManager {
    boolean flag;
    double price = 0;
    double stock;
    List<String> title = new ArrayList<>();

    public ReplenishManager(boolean flag){
        this.flag = flag;
    }

    public ReplenishManager(boolean flag,List<String> title){
        this.title = title;
        this.flag = flag;
    }


    public void PackagingClass(double money, String user_id, List<String> item_id, List<Double> amount,List<Double> stock){
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

        List<IncomingOrderdetail> incomingOrderDetail = new ArrayList<>();
        List<Item_stock> item_stocks = new ArrayList<>();

        for (int i = 0 ; i < amount.size() ; i++){
            IncomingOrderdetail incomingOrderdetail = new IncomingOrderdetail();
            incomingOrderdetail.setIncoming_orderbase_id(id);
            incomingOrderdetail.setItem_id(item_id.get(i));
            incomingOrderdetail.setAmount(amount.get(i));

            Item_stock item_stock = new Item_stock();
            item_stock.setStock(stock.get(i));
            item_stock.setItem_id(item_id.get(i));

            incomingOrderDetail.add(incomingOrderdetail);
            item_stocks.add(item_stock);
        }

        IncomingOrderService replenishStockService = new IncomingOrderServiceImpl();
        List<Item> items = new ArrayList<>();
        if (flag){
            for (int i = 0 ; i < title.size() ; i++) {
                Item item = new Item();
                item.setTitle(title.get(i));
                item.setPrice(price);
                item.setSales(0);
                item.setId(item_id.get(i));

                items.add(item);
            }

            replenishStockService.newIncomingOrderCaseTwo(incomingOrderDetail,incomingOrderbase,item_stocks,items);
        }else{
            replenishStockService.newIncomingOrderCaseOne(incomingOrderDetail,incomingOrderbase,item_stocks);
        }

    }
}
