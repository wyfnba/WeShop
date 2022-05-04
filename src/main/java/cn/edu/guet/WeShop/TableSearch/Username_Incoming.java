package cn.edu.guet.WeShop.TableSearch;

import java.sql.Timestamp;
import java.util.Objects;

public class Username_Incoming {
    private String  username;
    private double money;
    private Timestamp time;

    public Username_Incoming() {
    }

    public Username_Incoming(String username, double money, Timestamp time) {
        this.username = username;
        this.money = money;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        Username_Incoming that = (Username_Incoming) o;
        return Double.compare(that.money, money) == 0 && Objects.equals(username, that.username) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, money, time);
    }

    @Override
    public String toString() {
        return "Username_Incoming{" +
                "username='" + username + '\'' +
                ", money=" + money +
                ", time=" + time +
                '}';
    }
}
