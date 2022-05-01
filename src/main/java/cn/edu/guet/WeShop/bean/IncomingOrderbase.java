package cn.edu.guet.WeShop.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class IncomingOrderbase {
    private String id;
    private String user_id;
    private double money;
    private Timestamp time;

    public IncomingOrderbase(){

    }

    public IncomingOrderbase(String id, String user_id, double money, Timestamp time) {
        this.id = id;
        this.user_id = user_id;
        this.money = money;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingOrderbase that = (IncomingOrderbase) o;
        return Double.compare(that.money, money) == 0 && Objects.equals(id, that.id) && Objects.equals(user_id, that.user_id) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, money, time);
    }

    @Override
    public String toString() {
        return "IncomingOrderbase{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", money=" + money +
                ", time=" + time +
                '}';
    }
}
