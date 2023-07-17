package com.formos.smoothie.component.menu;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Controller;
import com.formos.smoothie.model.Menu;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    public List<Menu> retrieveAllMenu() {
        return menuService.findAll();
    }

    public void updatePrice() {
        menuService.updatePrice();
    }
}
