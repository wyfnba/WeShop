package cn.edu.guet.WeShop.TableSearch;

import java.sql.Timestamp;
import java.util.Objects;

public class User_Orderbase {
    private String userName;
    private String status;
    private Double price;
    private Timestamp time;

    public User_Orderbase() {
    }

    public User_Orderbase(String userName, String status, Double price, Timestamp time) {
        this.userName = userName;
        this.status = status;
        this.price = price;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        User_Orderbase that = (User_Orderbase) o;
        return Objects.equals(userName, that.userName) && Objects.equals(status, that.status) && Objects.equals(price, that.price) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, status, price, time);
    }

    @Override
    public String toString() {
        return "User_Orderbase{" +
                "userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
