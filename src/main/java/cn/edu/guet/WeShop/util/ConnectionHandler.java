package cn.edu.guet.WeShop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {

    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConn() throws SQLException {
        /*
        为了保证OrderDaoImpl和StockDaoImpl中的Connection是同一个，我们做如下处理
         */
        Connection conn=threadLocal.get();
        /*
        null说明什么？
         */
        if(conn==null){
            String user = "root";
            String dbPassword = "wyfnb666";
            String url = "jdbc:mysql://47.94.211.86:3306/shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            conn = DriverManager.getConnection(url, user, dbPassword);
            threadLocal.set(conn);
        }
        return conn;
    }
    public static void closeConnection() throws SQLException {
        Connection conn=threadLocal.get();
        if(conn!=null){
            conn.close();
            threadLocal.remove();
        }
    }
}
