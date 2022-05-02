package cn.edu.guet.WeShop.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Orderbase {
    private String id;
    private int mch_id;
    private String out_trade_no;
    private Timestamp time_end;
    private String transaction_id;
    private String user_id;
    private double order_price;

    public Orderbase(){

    }

    public Orderbase(String id, int mch_id, String out_trade_no, Timestamp time_end, String transaction_id, String user_id, double order_price) {
        this.id = id;
        this.mch_id = mch_id;
        this.out_trade_no = out_trade_no;
        this.time_end = time_end;
        this.transaction_id = transaction_id;
        this.user_id = user_id;
        this.order_price = order_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMch_id() {
        return mch_id;
    }

    public void setMch_id(int mch_id) {
        this.mch_id = mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Timestamp getTime_end() {
        return time_end;
    }

    public void setTime_end(Timestamp time_end) {
        this.time_end = time_end;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderbase orderbase = (Orderbase) o;
        return mch_id == orderbase.mch_id && Double.compare(orderbase.order_price, order_price) == 0 && Objects.equals(id, orderbase.id) && Objects.equals(out_trade_no, orderbase.out_trade_no) && Objects.equals(time_end, orderbase.time_end) && Objects.equals(transaction_id, orderbase.transaction_id) && Objects.equals(user_id, orderbase.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mch_id, out_trade_no, time_end, transaction_id, user_id, order_price);
    }

    @Override
    public String toString() {
        return "Orderbase{" +
                "id='" + id + '\'' +
                ", mch_id=" + mch_id +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", time_end=" + time_end +
                ", transaction_id='" + transaction_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", order_price=" + order_price +
                '}';
    }
}
