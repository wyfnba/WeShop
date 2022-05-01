package cn.edu.guet.WeShop.bean;

import java.util.Objects;

public class Orderdetail {
    private String id;
    private String orderbase_id;
    private String item_id;
    private int amount;

    public Orderdetail(){

    }

    public Orderdetail(String id, String orderbase_id, String item_id, int amount) {
        this.id = id;
        this.orderbase_id = orderbase_id;
        this.item_id = item_id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderbase_id() {
        return orderbase_id;
    }

    public void setOrderbase_id(String orderbase_id) {
        this.orderbase_id = orderbase_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderdetail that = (Orderdetail) o;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(orderbase_id, that.orderbase_id) && Objects.equals(item_id, that.item_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderbase_id, item_id, amount);
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "id='" + id + '\'' +
                ", orderbase_id='" + orderbase_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
