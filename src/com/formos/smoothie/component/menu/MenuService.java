package com.formos.smoothie.component.menu;

import com.formos.smoothie.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findAll();

    void updatePrice();

    List<String> doCheckIngredient();
}
