package com.formos.smoothie.component.menu;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Service;
import com.formos.smoothie.model.Inventory;
import com.formos.smoothie.model.Menu;
import com.formos.smoothie.repository.IngredientRepository;
import com.formos.smoothie.repository.InventoryRepository;
import com.formos.smoothie.repository.MenuRepository;
import com.formos.smoothie.repository.RecipeRepository;
import com.formos.smoothie.utils.CommonConstants;
import com.formos.smoothie.utils.SmoothieUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public void updatePrice() {
        List<Menu> menuList = menuRepository.findAll().stream().map(menu -> {
            AtomicLong newPrice = new AtomicLong();
            recipeRepository.findByIngredient(ingredientRepository.findByMenuId(menu.getId())).forEach(recipe -> {
                Inventory inventory = inventoryRepository.findById(recipe.getInventoryId());
                recipe.setInventory(inventory);
                if (Optional.ofNullable(inventory).isPresent()) {
                    newPrice.getAndAdd(SmoothieUtil.getCost(recipe));
                }
            });
            long updPrice = new BigDecimal(newPrice.get() / CommonConstants.FOOD_COST_PER).setScale(-3, RoundingMode.UP).longValue();
            menu.setPrice(updPrice);
            return menu;
        }).collect(Collectors.toList());
        menuRepository.updateAll(menuList);
    }
}
