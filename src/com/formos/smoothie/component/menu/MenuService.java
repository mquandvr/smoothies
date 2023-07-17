package com.formos.smoothie.component.menu;

import com.formos.smoothie.model.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> findAll();

    public void updatePrice();

    public List<String> doCheckIngredient();
}
