package com.formos.smoothie.component.order;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Service;
import com.formos.smoothie.model.Ingredient;
import com.formos.smoothie.model.Inventory;
import com.formos.smoothie.model.Menu;
import com.formos.smoothie.model.Order;
import com.formos.smoothie.model.Recipe;
import com.formos.smoothie.repository.IngredientRepository;
import com.formos.smoothie.repository.InventoryRepository;
import com.formos.smoothie.repository.MenuRepository;
import com.formos.smoothie.repository.OrderRepository;
import com.formos.smoothie.repository.RecipeRepository;
import com.formos.smoothie.utils.CommonConstants;
import com.formos.smoothie.utils.CommonUtil;
import com.formos.smoothie.utils.SmoothieUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Order orderSmoothie(int menuId, int numOfCup) {
        AtomicBoolean isErr = new AtomicBoolean(false);
        Menu menuChoose = menuRepository.findById(menuId);
        Order newOrder = null;

        if (Optional.ofNullable(menuChoose).isPresent()) {

            System.out.printf("Customer order: %s%n", menuChoose.getName());
            System.out.printf("Number of cup: %s", numOfCup);
            System.out.printf("Size of cup: %s ml", CommonConstants.SIZE_OF_SMOOTHIE);

            List<Ingredient> ingredientForMenu = ingredientRepository.findByMenuId(menuChoose.getId());
            if (CommonUtil.isCollectionNotEmpty(ingredientForMenu)) {
                List<Recipe> recipeList = getRecipeList(ingredientForMenu);

                recipeList.forEach(recipe -> {
                    if(checkInventory(recipe, numOfCup)) {
                        trackInventory(recipe, numOfCup);
                    } else {
                        isErr.set(true);
                        System.out.println("There are not enough ingredients to make the smoothie");
                    }
                });
            } else {
                isErr.set(true);
                System.out.println(("Ingredient not found!"));
            }
        } else {
            isErr.set(true);
            System.out.println(("Menu not found!"));
        }

        if (!isErr.get()) {
            int orderId = orderRepository.count();
            newOrder = new Order();
            newOrder.setId(orderId == 0 ? 1 : ++orderId);
            newOrder.setOrderDate(LocalDateTime.now());
            newOrder.setMenuId(menuChoose.getId());
            newOrder.setName(menuChoose.getName());
            newOrder.setTotalPrice(menuChoose.getPrice() * numOfCup);
            newOrder.setNumOfCups(numOfCup);
            orderRepository.insert(newOrder);
        }

        return newOrder;
    }

    private List<Recipe> getRecipeList(List<Ingredient> ingredientForMenu) {
        return recipeRepository.findByIngredient(ingredientForMenu).stream().map(recipe -> {
            recipe.setInventory(inventoryRepository.findById(recipe.getInventoryId()));
            return recipe;
        }).collect(Collectors.toList());
    }

    private void trackInventory(Recipe recipeOfSmoothie, int numOfCup) {
        updateIngredients(recipeOfSmoothie, numOfCup);
    }

    private void updateIngredients(Recipe recipe, int numOfCup) {
        Inventory inventory = recipe.getInventory();
        int quantityFruitNeeded = SmoothieUtil.getQuantityFruitNeeded(recipe.getQuantity(), recipe.getRateOfBlended(), numOfCup);
        int quantityUpd = inventory.getQuantity() - quantityFruitNeeded;
        inventory.setQuantity(Math.max(quantityUpd, 0));
        inventoryRepository.update(inventory);
    }

    /**
     * Deny a sale when there are not enough ingredients to make the smoothie
     * @return boolean
     */
    private boolean checkInventory(Recipe recipe, int numOfCup) {
        int quanMaterial = SmoothieUtil.getQuantityFruitNeeded(recipe.getQuantity(), recipe.getRateOfBlended(), numOfCup);
        if (quanMaterial <= recipe.getInventory().getQuantity()) {
            return true;
        } else {
            System.out.printf("%-50s%-20s%s%n"
                    ,"Ingredient: " + recipe.getInventory().getName()
                    , "Need: " + quanMaterial + recipe.getInventory().getUnit()
                    , "Inventory: " + recipe.getInventory().getQuantity() + recipe.getInventory().getUnit());
            return false;
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
