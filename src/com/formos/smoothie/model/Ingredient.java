package com.formos.smoothie.model;

public class Ingredient {

    private int menuId;
    private int recipeId;

    public Ingredient() {
    }

    public Ingredient(int menuId, int recipeId) {
        this.menuId = menuId;
        this.recipeId = recipeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "menuId=" + menuId +
                ", recipeId=" + recipeId +
                '}';
    }
}
