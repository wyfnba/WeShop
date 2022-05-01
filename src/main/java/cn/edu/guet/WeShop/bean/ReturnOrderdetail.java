package cn.edu.guet.WeShop.bean;

import java.util.Objects;

public class ReturnOrderdetail {
    private String id;
    private String return_orderbase_id;
    private String item_id;
    private double amount;

    public ReturnOrderdetail(){

    }

    public ReturnOrderdetail(String id, String return_orderbase_id, String item_id, double amount) {
        this.id = id;
        this.return_orderbase_id = return_orderbase_id;
        this.item_id = item_id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturn_orderbase_id() {
        return return_orderbase_id;
    }

    public void setReturn_orderbase_id(String return_orderbase_id) {
        this.return_orderbase_id = return_orderbase_id;
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
        ReturnOrderdetail that = (ReturnOrderdetail) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(return_orderbase_id, that.return_orderbase_id) && Objects.equals(item_id, that.item_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, return_orderbase_id, item_id, amount);
    }

    @Override
    public String toString() {
        return "ReturnOrderdetail{" +
                "id='" + id + '\'' +
                ", return_orderbase_id='" + return_orderbase_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
