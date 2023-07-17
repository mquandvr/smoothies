package com.formos.smoothie.repository;

import com.formos.smoothie.model.Ingredient;
import com.formos.smoothie.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeRepository {

    private static List<Recipe> recipeList;

    static {
        recipeList = new ArrayList<>(
                Arrays.asList(new Recipe(1, "Blended Strawberry Recipe", 50, 1001, 1.0),
                        new Recipe(2, "Ice Recipe", 30, 2001, 1.0),
                        new Recipe(3,"Condensed milk Recipe", 20, 2002, 1.0),
                        new Recipe(4,"Sugar Recipe", 8, 2003, 1.0),
                        new Recipe(5, "Blended Banana Recipe", 50, 1002, 1.2),
                        new Recipe(6, "Blended Mango Recipe", 50, 1003, 1.4),
                        new Recipe(7, "Mix Blended Strawberry, Mango Recipe", 30, 1001, 1.0),
                        new Recipe(8, "Mix Blended Strawberry, Mango Recipe", 20, 1003, 1.4),
                        new Recipe(9, "Mix Blended Strawberry, Banana, Mango Recipe", 30, 1001, 1.0),
                        new Recipe(10, "Mix Blended Strawberry, Banana, Mango Recipe", 10, 1002, 1.2),
                        new Recipe(11, "Mix Blended Strawberry, Banana, Mango Recipe", 10, 1003, 1.4)
                )
        );
    }

    /**
     * SELECT * FROM RECIPE
     * @return
     */
    public List<Recipe> findAll() {
        return recipeList;
    }

    /**
     * SELECT * FROM RECIPE WHERE ID = ?
     * @param ingredientForMenu
     * @return
     */
    public List<Recipe> findByIngredient(List<Ingredient> ingredientForMenu) {
        return recipeList.stream()
                .filter(recipe -> ingredientForMenu.stream().anyMatch(i -> i.getRecipeId() == recipe.getId()))
                .sorted(Comparator.comparing(o -> Integer.valueOf(o.getInventoryId())))
                .collect(Collectors.toList());
    }
}
