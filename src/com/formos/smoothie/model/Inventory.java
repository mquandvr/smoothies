package com.formos.smoothie.model;

public class Inventory {

    private int id;

    private String name;
    private int quantity;
    private long price;

    private String unit;

    private long unitPrice;

    public Inventory(int id, String name, int quantity, long price, String unit, long unitPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
