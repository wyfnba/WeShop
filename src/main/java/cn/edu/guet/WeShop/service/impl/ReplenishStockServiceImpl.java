package cn.edu.guet.WeShop.service.impl;

import cn.edu.guet.WeShop.bean.IncomingOrderbase;
import cn.edu.guet.WeShop.bean.IncomingOrderdetail;
import cn.edu.guet.WeShop.bean.Item;
import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.dao.AddItemDao;
import cn.edu.guet.WeShop.dao.IncreaseItemStockDao;
import cn.edu.guet.WeShop.dao.ReplenishStockBaseDao;
import cn.edu.guet.WeShop.dao.ReplenishStockDetailDao;
import cn.edu.guet.WeShop.dao.impl.AddItemImpl;
import cn.edu.guet.WeShop.dao.impl.IncreaseItemImpl;
import cn.edu.guet.WeShop.dao.impl.ReplenishStockBaseImpl;
import cn.edu.guet.WeShop.dao.impl.ReplenishStockDetailImpl;
import cn.edu.guet.WeShop.service.ReplenishStockService;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 19:51:43
 */
public class ReplenishStockServiceImpl implements ReplenishStockService {
    Connection conn = null;
    @Override
    public void newIncomingOrderCaseOne(IncomingOrderdetail incomingOrderdetail, IncomingOrderbase incomingOrderbase, Item_stock item_stock) {


        try {
            ReplenishStockBaseDao replenishStockBaseDao = new ReplenishStockBaseImpl();
            ReplenishStockDetailDao replenishStockDetailDao = new ReplenishStockDetailImpl();
            IncreaseItemStockDao increaseItemStockDao = new IncreaseItemImpl();

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
            ReplenishStockBaseDao replenishStockBaseDao = new ReplenishStockBaseImpl();
            ReplenishStockDetailDao replenishStockDetailDao = new ReplenishStockDetailImpl();
            IncreaseItemStockDao increaseItemStockDao = new IncreaseItemImpl();
            AddItemDao addItemDao = new AddItemImpl();

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
