package cn.edu.guet.WeShop.bean;

import java.util.Objects;

public class IncomingOrderdetail {
    private String id;
    private String incoming_orderbase_id;
    private String item_id;
    private double amount;

    public IncomingOrderdetail(){

    }

    public IncomingOrderdetail(String id, String incoming_orderbase_id, String item_id, double amount) {
        this.id = id;
        this.incoming_orderbase_id = incoming_orderbase_id;
        this.item_id = item_id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncoming_orderbase_id() {
        return incoming_orderbase_id;
    }

    public void setIncoming_orderbase_id(String incoming_orderbase_id) {
        this.incoming_orderbase_id = incoming_orderbase_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingOrderdetail that = (IncomingOrderdetail) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(incoming_orderbase_id, that.incoming_orderbase_id) && Objects.equals(item_id, that.item_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incoming_orderbase_id, item_id, amount);
    }

    @Override
    public String toString() {
        return "IncomingOrderdetail{" +
                "id='" + id + '\'' +
                ", incoming_orderbase_id='" + incoming_orderbase_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
