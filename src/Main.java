//import com.formos.smoothie.model.Ingredient;
//import com.formos.smoothie.model.Inventory;
//import com.formos.smoothie.model.Menu;
//import com.formos.smoothie.model.Recipe;
//
//import java.text.DecimalFormat;
//import java.util.*;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import static com.formos.smoothie.utils.CommonUtil.isNotEmpty;
//
//public class Main {
//    public static final int TYPE_OF_RECIPE_AUX = 0;
//
//    public static final int SIZE_OF_SMOOTHIE = 300;
//
//    public static final List<Inventory> inventoryList = new ArrayList<>(
//            Arrays.asList(new Inventory(1001, "Strawberry", 1500, 100000),
//                    new Inventory(2001, "Ice", 1000, 100000),
//                    new Inventory(2002, "Condensed milk", 1000, 100000),
//                    new Inventory(2003, "Sugar", 1000, 100000),
//                    new Inventory(1002, "Banana", 1000, 100000),
//                    new Inventory(1003, "Mango", 1000, 100000)
//            )
//    );
//
//    public static final List<Recipe> recipeList = new ArrayList<>(
//            Arrays.asList(new Recipe(1, "Blended Strawberry Recipe", 50, 1001, 1.0),
//                        new Recipe(2, "Ice Recipe", 30, 2001, 1.0),
//                        new Recipe(3,"Condensed milk Recipe", 20, 2002, 1.0),
//                        new Recipe(4,"Sugar Recipe", 8, 2003, 1.0),
//                        new Recipe(5, "Blended Banana Recipe", 50, 1002, 1.2),
//                        new Recipe(6, "Blended Mango Recipe", 50, 1003, 1.4),
//                        new Recipe(7, "Blended Strawberry Recipe", 25, 1001, 1.0),
//                        new Recipe(8, "Blended Mango Recipe", 25, 1003, 1.4)
//            )
//    );
//
//    public static final List<Ingredient> ingredientList = new ArrayList<>(
//            Arrays.asList(new Ingredient(1, 1),
//                    new Ingredient(1, 2),
//                    new Ingredient(1, 3),
//                    new Ingredient(1, 4),
//                    new Ingredient(2, 5),
//                    new Ingredient(2, 2),
//                    new Ingredient(2, 3),
//                    new Ingredient(2, 4),
//                    new Ingredient(3, 6),
//                    new Ingredient(3, 2),
//                    new Ingredient(3, 3),
//                    new Ingredient(3, 4),
//                    new Ingredient(4, 7),
//                    new Ingredient(4, 8),
//                    new Ingredient(4, 2),
//                    new Ingredient(4, 3),
//                    new Ingredient(4, 4)
//            )
//    );
//
//    public static final List<Menu> menuList = new ArrayList<>(
//            Arrays.asList(new Menu(1, "Strawberry Smoothie", 1, 50000),
//                    new Menu(2, "Banana Smoothie", 2, 60000),
//                    new Menu(3, "Mango Smoothie", 3, 70000),
//                    new Menu(4, "Mix Strawberry and Mango Smoothie", 4, 90000)
//            )
//    );
//
//    public static void main(String[] args) {
//
//        int numOfCup = 2;
//        int offer = 4;
//
//        Menu menuChoose = menuList.stream().filter(m -> m.getId() == offer).findFirst().orElse(null);
//
//        if (Optional.ofNullable(menuChoose).isPresent()) {
//
//            System.out.println(String.format("Customer order: %s", menuChoose.getName()));
//            System.out.println(String.format("Number of cup: %s", numOfCup));
//            System.out.println(String.format("Size of cup: %s ml", SIZE_OF_SMOOTHIE));
//
//            List<Ingredient> ingredientForMenu = ingredientList.stream().filter(i -> i.getMenuId() == menuChoose.getId())
//                    .sorted(Comparator.comparing(o -> Integer.valueOf(o.getMenuId())))
//                    .collect(Collectors.toList());
//
//            if (isNotEmpty(ingredientForMenu)) {
//                List<Recipe> recipeOfSmoothie = recipeList.stream()
//                        .filter(recipe -> ingredientForMenu.stream().anyMatch(i -> i.getRecipeId() == recipe.getId()))
//                        .sorted(Comparator.comparing(o -> Integer.valueOf(o.getInventoryId())))
//                        .collect(Collectors.toList());
//
//                if (isNotEmpty(recipeOfSmoothie)) {
//                    List<Inventory> inventoryForRecipe = inventoryList.stream()
//                            .filter(inventory -> recipeOfSmoothie.stream().anyMatch(r -> r.getInventoryId() == inventory.getId()))
//                            .sorted(Comparator.comparing(o -> Integer.valueOf(o.getId())))
//                            .collect(Collectors.toList());
//
//                    System.out.println("INVENTORY CURRENT----------");
//
//                    inventoryForRecipe.forEach(System.out::println);
//
//                    System.out.println("----------");
//
//                    order(inventoryForRecipe, recipeOfSmoothie, numOfCup, offer);
//
//                    System.out.println("\nINVENTORY AFTER ORDER----------");
//
//                    inventoryForRecipe.forEach(System.out::println);
//
//                    System.out.println("-----------");
//
//                    DecimalFormat format = new DecimalFormat("#,###");
//                    System.out.printf("Bill: %s VND%n", format.format(menuChoose.getPrice() * numOfCup));
//                }
//            } else {
//                System.out.println(("Ingredient not found!"));
//            }
//        } else {
//            System.out.println(("Menu choose not found!"));
//        }
//    }
//
//    public static void order(List<Inventory> inventories, List<Recipe> recipeOfSmoothie, int numOfCup, int offer) {
//        if(checkInventory(inventories, recipeOfSmoothie, numOfCup, offer)) {
//            trackInventory(inventories, recipeOfSmoothie, numOfCup, offer);
//        }
//    }
//
//    private static void trackInventory(List<Inventory> inventories, List<Recipe> recipeOfSmoothie, int numOfCup, int offer) {
//        recipeOfSmoothie.stream().forEach(recipe -> {
//            inventories.stream().filter(i -> i.getId() == recipe.getInventoryId()).forEach(inventory -> {
//                updateIngredients(inventory, recipe, numOfCup);
//            });
//        });
//    }
//
//    private static void updateIngredients(Inventory inventory, Recipe recipe, int numOfCup) {
//        double quanMaterial = getQuanIngredients(recipe.getQuantity(), recipe.getRateOfBlended(), numOfCup);
//        int quantity = (int) (inventory.getQuantity() - quanMaterial);
//        inventory.setQuantity(quantity);
//    }
//
//    /**
//     * For every 100ml of smoothie, her recipe requires 50ml of blended fruit, 30ml of ice, 20ml of
//     * condensed milk, and 8g of sugar
//     * @return
//     */
//    public static boolean checkInventory(List<Inventory> inventories, List<Recipe> recipeOfSmoothie, int numOfCup, int offer) {
//        return recipeOfSmoothie.stream().allMatch(recipe -> InventoryPredicate(inventories, numOfCup, recipe));
//    }
//
//    private static boolean InventoryPredicate(List<Inventory> inventories, int numOfCup, Recipe recipe) {
//        Inventory inventory = inventories.stream().filter(i -> i.getId() == recipe.getInventoryId()).findFirst().orElse(null);
//        if (Optional.ofNullable(inventory).isPresent()) {
//            return checkMaterialForSize(inventory, recipe, numOfCup);
//        } else {
//            System.out.println(String.format("Ingredients {%s} not found in inventory", recipe.getName()));
//            return false;
//        }
//    }
//
//    private static boolean checkMaterialForSize(Inventory inventory, Recipe recipe, int numOfCup) {
//        double quanMaterial = getQuanIngredients(recipe.getQuantity(), recipe.getRateOfBlended(), numOfCup);
//        System.out.println(String.format("%-25s%-20s", "Quantity: " + inventory.getName(), "Used:" + quanMaterial));
//        if (quanMaterial <= inventory.getQuantity()) {
//            return true;
//        } else {
//            System.out.println(String.format("%-25s%-20s%s","Not Enough: Name: " + inventory.getName(), "Inventory: " + inventory.getQuantity(), "Need: " + quanMaterial));
//        }
//
//        return false;
//    }
//
//    private static double getQuanIngredients(int quanRecipe, double rateOfBlended, int numOfCup) {
//        return (rateOfBlended * quanRecipe * (SIZE_OF_SMOOTHIE / 100)) * numOfCup;
//    }
//}