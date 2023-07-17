package com.formos.smoothie.repository;

import com.formos.smoothie.model.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuRepository {

    private static List<Menu> menuList;

    static {
        menuList = new ArrayList<>(
                Arrays.asList(new Menu(1, "Strawberry Smoothie", 1, 0),
                        new Menu(2, "Banana Smoothie", 2, 0),
                        new Menu(3, "Mango Smoothie", 3, 0),
                        new Menu(4, "Mix Strawberry and Mango Smoothie", 4, 0),
                        new Menu(5, "Mix Strawberry, Banana and Mango Smoothie", 5, 0)
                )
        );
    }

    /**
     * SELECT * FROM MENU
     * @return
     */
    public List<Menu> findAll() {
        return menuList;
    }

    /**
     * SELECT * FROM MENU WHERE ID = ?
     * @param id
     * @return
     */
    public Menu findById(int id) {
        return menuList.stream().filter(menu -> menu.getId() == id).findFirst().orElse(null);
    }

    /**
     * UPDATE MENU SET ? = ?
     * @param menuListUpd
     */
    public void updateAll(List<Menu> menuListUpd) {
        menuList = menuListUpd;
    }
}
