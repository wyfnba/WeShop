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

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 19:51:43
 */
public class IncomingOrderServiceImpl implements IncomingOrderService {
    Connection conn = null;
    @Override
    public void newIncomingOrderCaseOne(IncomingOrderdetail incomingOrderdetail, IncomingOrderbase incomingOrderbase, Item_stock item_stock) {


        try {
            IncomingOrderBaseDao replenishStockBaseDao = new IncomingOrderBaseImpl();
            IncomingOrderDetailDao replenishStockDetailDao = new IncomingOrderDetailImpl();
            ItemStockDao increaseItemStockDao = new ItemStockImpl();

            conn = ConnectionHandler.getConn();

            conn.setAutoCommit(false);

            replenishStockBaseDao.addOrderBase(incomingOrderbase);
            replenishStockDetailDao.addOrderDetail(incomingOrderdetail);
            increaseItemStockDao.increaseItemStock(item_stock);

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
    public void newIncomingOrderCaseTwo(IncomingOrderdetail incomingOrderdetail, IncomingOrderbase incomingOrderbase, Item_stock item_stock, Item item) {

        try {
            IncomingOrderBaseDao replenishStockBaseDao = new IncomingOrderBaseImpl();
            IncomingOrderDetailDao replenishStockDetailDao = new IncomingOrderDetailImpl();
            ItemStockDao increaseItemStockDao = new ItemStockImpl();
            ItemDao addItemDao = new ItemImpl();

            conn = ConnectionHandler.getConn();

            conn.setAutoCommit(false);

            addItemDao.AddItem(item);
            replenishStockBaseDao.addOrderBase(incomingOrderbase);
            replenishStockDetailDao.addOrderDetail(incomingOrderdetail);
            increaseItemStockDao.increaseItemStock(item_stock);


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
