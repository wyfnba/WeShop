package cn.edu.guet.WeShop.manager;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.service.IncomingOrderService;
import cn.edu.guet.WeShop.service.impl.IncomingOrderServiceImpl;

import java.util.*;

/**
 * @Author liwei
 * @Date 2021-08-04 11:28
 * @Version 1.0
 */
public class ReplenishManager {
    boolean flag;
    String title;
    List<String> list = new ArrayList<>();

    public ReplenishManager(){

    }

    public ReplenishManager(boolean flag){
        this.flag = flag;
    }

    public ReplenishManager(boolean flag,String title){
        this.title = title;
        this.flag = flag;
    }


    public void PackagingClass(double money, String user_id, List<String> item_ids, List<Double> amounts, List<Double> stocks, LinkedHashMap<String,String> hm){
        randomNumber();

        IncomingOrderbase incomingOrderbase = new IncomingOrderbase();
        String id = UUID.randomUUID().toString().replace("-", "");
        incomingOrderbase.setId(id);//会自动生成32个字符
        incomingOrderbase.setUser_id(user_id);
        incomingOrderbase.setMoney(money);

        Set<String> keySet = hm.keySet();
        int i = 0;//因为item_ids、amounts、stocks、hm的长度应该是一样的
        List<Item> items = new ArrayList<>();
        IncomingOrderService replenishStockService = new IncomingOrderServiceImpl(incomingOrderbase);
        List<IncomingOrderdetail> incomingOrderDetails = new ArrayList<>();
        List<Item_stock> item_stocks = new ArrayList<>();
        LinkedHashMap<String,Item_stock> itemStocks = new LinkedHashMap<>();

        for (String s : keySet) {
            String s1 = list.get(0);
            list.remove(0);
            //这个item_id代表了Item表的id、Item_stock表里的item_id和incomingOrderDetail表里的item_id

            IncomingOrderdetail incomingOrderdetail = new IncomingOrderdetail();
            incomingOrderdetail.setItem_id(item_ids.get(i));
            incomingOrderdetail.setIncoming_orderbase_id(id);
            incomingOrderdetail.setAmount(amounts.get(i));

            incomingOrderDetails.add(incomingOrderdetail);

            Item_stock item_stock = new Item_stock();
            item_stock.setItem_id(item_ids.get(i));
            item_stock.setStock(amounts.get(i));

            item_stocks.add(item_stock);

            if (s.contains("yes")){
                //如果需要添加商品表

                String title = hm.get(s);
                Item item = new Item();
                item.setId(item_ids.get(i));
                item.setTitle(title);
                item.setPrice(0);
                item.setSales(0);

                items.add(item);

                s1 = s1+"yes";
            }
            itemStocks.put(s1,item_stock);
            i++;
        }
        if (items.size()==0){
            replenishStockService.newIncomingOrderCaseOne(incomingOrderDetails,item_stocks);
        }else{
            replenishStockService.newIncomingOrderCaseTwo(incomingOrderDetails,itemStocks,items);
        }

    }

    //随机生成50个不重复的数字
    private void randomNumber(){
        String number;
        for(int i = 0 ; i < 50 ; i++){
            while(true){
                number = String.valueOf((int)(Math.random()*76));
                if(!list.contains(number)){
                    //如果不包含该值
                    list.add(number);
                    break;
                }
            }
        }
    }
}