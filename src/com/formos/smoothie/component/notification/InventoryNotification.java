package com.formos.smoothie.component.notification;

import com.formos.smoothie.model.Recipe;
import com.formos.smoothie.utils.SmoothieUtil;

public class InventoryNotification implements InventoryIObserverService {

    @Override
    public void update(Recipe recipe) {
        int quanMaterial = SmoothieUtil.getQuantityFruitNeeded(recipe.getQuantity(), recipe.getRateOfBlended(), 4);
        if (quanMaterial >= recipe.getInventory().getQuantity()) {
            System.out.println(String.format("Warning - Ingredient %s gets below the level required of %s%s to make 4 more smoothies!", recipe.getInventory().getName(), quanMaterial, recipe.getInventory().getUnit()));
        }
    }
}
