package com.formos.smoothie.model;

public class Revenue {

    private long income;

    private long capital;

    private long ingredientCost;

    private long operatingCosts;

    private long totalSalePrice;

    private String message;

    public Revenue() {
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public long getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    public long getIngredientCost() {
        return ingredientCost;
    }

    public void setIngredientCost(long ingredientCost) {
        this.ingredientCost = ingredientCost;
    }

    public long getOperatingCosts() {
        return operatingCosts;
    }

    public void setOperatingCosts(long operatingCosts) {
        this.operatingCosts = operatingCosts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(long totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "income=" + income +
                ", capital=" + capital +
                ", ingredientCost=" + ingredientCost +
                ", operatingCosts=" + operatingCosts +
                ", totalSalePrice=" + totalSalePrice +
                ", message='" + message + '\'' +
                '}';
    }
}