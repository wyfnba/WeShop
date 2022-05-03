package cn.edu.guet.WeShop.service.impl;

import cn.edu.guet.WeShop.bean.Item_stock;
import cn.edu.guet.WeShop.bean.Orderbase;
import cn.edu.guet.WeShop.bean.Orderdetail;
import cn.edu.guet.WeShop.dao.ItemStockDao;
import cn.edu.guet.WeShop.dao.OrderbaseDao;
import cn.edu.guet.WeShop.dao.OrderdetailDao;
import cn.edu.guet.WeShop.dao.impl.ItemStockImpl;
import cn.edu.guet.WeShop.dao.impl.OrderbaseDaoImpl;
import cn.edu.guet.WeShop.dao.impl.OrderdetailDaoImpl;
import cn.edu.guet.WeShop.service.OrderService;
import cn.edu.guet.WeShop.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public void addOrder(Orderbase orderbase, List<Orderdetail> orderdetailList) throws SQLException {
        Connection conn = null;
        try {
            OrderbaseDao orderbaseDao=new OrderbaseDaoImpl();
            OrderdetailDao orderdetailDao=new OrderdetailDaoImpl();
            ItemStockDao itemStockDao=new ItemStockImpl();

            conn = ConnectionHandler.getConn();

            //开启事务（必须先有Connection）
            conn.setAutoCommit(false);

            orderbaseDao.addOrder(orderbase);
            for(int i=0;i<orderdetailList.size();i++){
                orderdetailDao.addOrder(orderdetailList.get(i));
                Item_stock item_stock=new Item_stock(orderdetailList.get(i).getAmount(),orderdetailList.get(i).getItem_id());
                itemStockDao.decreaseItemStock(item_stock);
            }


            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
                System.out.println("事务回滚.................................................");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                ConnectionHandler.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
