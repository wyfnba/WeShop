package cn.edu.guet.WeShop.bean;

import java.util.Objects;

/**
 * @Author Pangjiaen
 * @Date 2022/5/1 17:42:22
 */
public class Item_stock {
    private String id;
    private double stock;
    private String item_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item_stock)) return false;
        Item_stock that = (Item_stock) o;
        return stock == that.stock && Objects.equals(id, that.id) && Objects.equals(item_id, that.item_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, item_id);
    }

    @Override
    public String toString() {
        return "Item_stock{" +
                "id='" + id + '\'' +
                ", stock=" + stock +
                ", item_id='" + item_id + '\'' +
                '}';
    }
}
