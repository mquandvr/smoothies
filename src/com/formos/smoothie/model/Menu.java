package com.formos.smoothie.model;

public class Menu {
    private int id;
    private String name;
    private int recipeId;
    private long price;

    public Menu() {
    }

    public Menu(int id, String name, int recipeId, long price) {
        this.id = id;
        this.name = name;
        this.recipeId = recipeId;
        this.price = price;
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

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", recipeId='" + recipeId + '\'' +
                ", price=" + price +
                '}';
    }
}
