package cn.edu.guet.WeShop.service.impl;

import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.bean.ReturnOrderbase;
import cn.edu.guet.WeShop.bean.ReturnOrderdetail;
import cn.edu.guet.WeShop.dao.ItemStockDao;
import cn.edu.guet.WeShop.dao.ReturnOrderBaseDao;
import cn.edu.guet.WeShop.dao.ReturnOrderDetailDao;
import cn.edu.guet.WeShop.dao.impl.ItemStockImpl;
import cn.edu.guet.WeShop.dao.impl.ReturnOrderBaseImpl;
import cn.edu.guet.WeShop.dao.impl.ReturnOrderDetailImpl;
import cn.edu.guet.WeShop.service.ReturnOrderService;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Pangjiaen
 * @Date 2022/5/8 19:16:02
 */
public class ReturnOrderServiceImpl implements ReturnOrderService {
    ReturnOrderbase returnOrderbase;
    public ReturnOrderServiceImpl(ReturnOrderbase returnOrderbase) {
        this.returnOrderbase = returnOrderbase;
    }

    @Override
    public void newReturnOrder(List<ReturnOrderdetail> returnOrderDetails, List<Item_stock> item_stocks) {
        ReturnOrderBaseDao returnOrderBaseDao = new ReturnOrderBaseImpl();
        ReturnOrderDetailDao returnOrderDetailDao = new ReturnOrderDetailImpl();
        ItemStockDao itemStockDao = new ItemStockImpl();

        Connection conn;
        try {
            conn = ConnectionHandler.getConn();

            conn.setAutoCommit(false);

            returnOrderBaseDao.addOrderBase(returnOrderbase);

            for (int i = 0 ; i < returnOrderDetails.size() ; i++){
                returnOrderDetailDao.addOrderDetail(returnOrderDetails.get(i));
            }

            for(int i = 0 ; i < item_stocks.size() ; i++){
                itemStockDao.decreaseItemStock(item_stocks.get(i));
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
