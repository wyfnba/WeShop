package cn.edu.guet.WeShop.service.impl;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.dao.ItemDao;
import cn.edu.guet.WeShop.dao.ItemStockDao;
import cn.edu.guet.WeShop.dao.IncomingOrderBaseDao;
import cn.edu.guet.WeShop.dao.IncomingOrderDetailDao;
import cn.edu.guet.WeShop.dao.impl.ItemImpl;
import cn.edu.guet.WeShop.dao.impl.ItemStockImpl;
import cn.edu.guet.WeShop.dao.impl.IncomingOrderBaseImpl;
import cn.edu.guet.WeShop.dao.impl.IncomingOrderDetailImpl;
import cn.edu.guet.WeShop.service.IncomingOrderService;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 19:51:43
 */
public class IncomingOrderServiceImpl implements IncomingOrderService {
    Connection conn = null;
    IncomingOrderbase incomingOrderbase;

    public IncomingOrderServiceImpl(){

    }

    public IncomingOrderServiceImpl(IncomingOrderbase incomingOrderbase){
        this.incomingOrderbase = incomingOrderbase;
    }

    @Override
    public void newIncomingOrderCaseOne(List<IncomingOrderdetail> incomingOrderDetail, List<Item_stock> item_stocks) {


        try {
            IncomingOrderBaseDao replenishStockBaseDao = new IncomingOrderBaseImpl();
            IncomingOrderDetailDao replenishStockDetailDao = new IncomingOrderDetailImpl();
            ItemStockDao increaseItemStockDao = new ItemStockImpl();

            conn = ConnectionHandler.getConn();

            conn.setAutoCommit(false);

            replenishStockBaseDao.addOrderBase(incomingOrderbase);

            for (int i = 0 ; i < incomingOrderDetail.size() ; i++){
                replenishStockDetailDao.addOrderDetail(incomingOrderDetail.get(i));
            }
            for (int i = 0 ; i < item_stocks.size() ; i++){
                increaseItemStockDao.increaseItemStock(item_stocks.get(i));
            }


            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("事务回滚");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                ConnectionHandler.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void newIncomingOrderCaseTwo(List<IncomingOrderdetail> incomingOrderDetail, HashMap<String,Item_stock> item_stock, List<Item> item) {

        try {
            IncomingOrderBaseDao replenishStockBaseDao = new IncomingOrderBaseImpl();
            IncomingOrderDetailDao replenishStockDetailDao = new IncomingOrderDetailImpl();
            ItemStockDao increaseItemStockDao = new ItemStockImpl();
            ItemDao addItemDao = new ItemImpl();

            conn = ConnectionHandler.getConn();

            conn.setAutoCommit(false);

            replenishStockBaseDao.addOrderBase(incomingOrderbase);

            for (int i = 0 ; i < item.size() ; i++){
                addItemDao.AddItem(item.get(i));
            }
            for (int i = 0 ; i < incomingOrderDetail.size() ; i++){
                replenishStockDetailDao.addOrderDetail(incomingOrderDetail.get(i));
            }

            Set<String> keySet = item_stock.keySet();
            for (String s : keySet){
                if (s.contains("yes")){
                    increaseItemStockDao.insertItemStock(item_stock.get(s));
                }else{
                    increaseItemStockDao.increaseItemStock(item_stock.get(s));
                }
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("事务回滚");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                ConnectionHandler.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
