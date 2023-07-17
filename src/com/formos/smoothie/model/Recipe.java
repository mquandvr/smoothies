package com.formos.smoothie.model;

public class Recipe {

    private int id;

    private String name;

    private int quantity;

    private int inventoryId;

    private double rateOfBlended;

    private Inventory inventory;

    public Recipe(int id, String name, int quantity, int inventoryId, double rateOfBlended) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
        this.rateOfBlended = rateOfBlended;
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

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public double getRateOfBlended() {
        return rateOfBlended;
    }

    public void setRateOfBlended(double rateOfBlended) {
        this.rateOfBlended = rateOfBlended;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", inventoryId=" + inventoryId +
                ", rateOfBlended=" + rateOfBlended +
                '}';
    }
}
