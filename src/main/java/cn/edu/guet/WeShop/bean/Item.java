package cn.edu.guet.WeShop.bean;

import java.util.Objects;

public class Item {
    private String id;
    private String title;
    private double price;
    private String description;
    private int sales;

    public Item() {
    }

    public Item(String id, String title, Float price, String description, int sales) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.sales = sales;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && sales == item.sales && Objects.equals(title, item.title) && Objects.equals(price, item.price) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description, sales);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                '}';
    }
}
