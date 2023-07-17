package com.formos.smoothie.repository;

import com.formos.smoothie.model.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IngredientRepository {

    private static List<Ingredient> ingredientList;

    static {
        ingredientList = new ArrayList<>(
            Arrays.asList(new Ingredient(1, 1),
                    new Ingredient(1, 2),
                    new Ingredient(1, 3),
                    new Ingredient(1, 4),
                    new Ingredient(2, 5),
                    new Ingredient(2, 2),
                    new Ingredient(2, 3),
                    new Ingredient(2, 4),
                    new Ingredient(3, 6),
                    new Ingredient(3, 2),
                    new Ingredient(3, 3),
                    new Ingredient(3, 4),
                    new Ingredient(4, 7),
                    new Ingredient(4, 8),
                    new Ingredient(4, 2),
                    new Ingredient(4, 3),
                    new Ingredient(4, 4),
                    new Ingredient(5, 9),
                    new Ingredient(5, 10),
                    new Ingredient(5, 11),
                    new Ingredient(5, 2),
                    new Ingredient(5, 3),
                    new Ingredient(5, 4)
            )
        );
    }

    /**
     * SELECT * FROM INGREDIENT WHERE MENU_ID = ?
     * @param menuId
     * @return
     */
    public List<Ingredient> findByMenuId(int menuId) {
        return ingredientList.stream().filter(ingredient -> ingredient.getMenuId() == menuId)
                .sorted(Comparator.comparing(o -> Integer.valueOf(o.getMenuId())))
                .collect(Collectors.toList());
    }
}
