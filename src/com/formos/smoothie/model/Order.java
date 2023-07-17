package com.formos.smoothie.model;

import java.time.LocalDateTime;

public class Order {

    private int id;

    private String name;

    private int menuId;

    private LocalDateTime orderDate;

    private int numOfCups;

    private long totalPrice;

    public Order() {
    }

    public Order(int id, String name, int menuId, LocalDateTime orderDate, int numOfCups, long totalPrice) {
        this.id = id;
        this.name = name;
        this.menuId = menuId;
        this.orderDate = orderDate;
        this.numOfCups = numOfCups;
        this.totalPrice = totalPrice;
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

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumOfCups() {
        return numOfCups;
    }

    public void setNumOfCups(int numOfCups) {
        this.numOfCups = numOfCups;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menuId=" + menuId +
                ", orderDate=" + orderDate +
                ", numOfCups=" + numOfCups +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
