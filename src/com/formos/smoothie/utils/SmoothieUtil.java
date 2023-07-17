package com.formos.smoothie.utils;

import com.formos.smoothie.model.Recipe;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SmoothieUtil {

    /**
     * For every 100ml of smoothie, her recipe requires 50ml of blended fruit, 30ml of ice, 20ml of
     * condensed milk, and 8g of sugar. She sells only 1 size of smoothie, 300ml
     * <br>
     * Example: Find value:
     * <pre>{@code
     *     100g  --> 100ml
     *     value --> 150ml(50ml * 3)
     *     value = ( 150ml * 100g ) / 100ml = 150g
     * }</pre>
     * @param quanRecipe
     * @param rateOfBlended
     * @param numOfCup
     * @return
     */
    public static int getQuantityFruitNeeded(int quanRecipe, double rateOfBlended, int numOfCup) {
        return (int) ((rateOfBlended * quanRecipe * (CommonConstants.SIZE_OF_SMOOTHIE / 100)) * numOfCup);
    }

    public static long getCost(Recipe recipe) {
        double cost = (recipe.getInventory().getUnitPrice() * recipe.getQuantity() * (CommonConstants.SIZE_OF_SMOOTHIE / 100) * recipe.getRateOfBlended());
        BigDecimal decimal = new BigDecimal(cost);
        return decimal.setScale(-2, RoundingMode.UP).longValue();
    }
}
