package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.menu.MenuController;
import com.formos.smoothie.model.Menu;
import com.formos.smoothie.utils.CommonUtil;

import java.util.List;

public class MenuResource implements ResourceService {
    @Override
    public void execute(ApplicationContext context, Object... args) throws Exception {
        System.out.println("Enter your menu choice: ");
        MenuController menuController = context.getInstance(MenuController.class);
        menuController.updatePrice();
        List<Menu> menuList = menuController.retrieveAllMenu();
        String formatRp = "%-5s%-50s%15s";
        menuList.stream().forEach(menu -> {
            System.out.println(String.format("%d- %-50s - %s", menu.getId(), menu.getName(), CommonUtil.convertMoney(menu.getPrice())));
        });
        System.out.print("Choose your menu: ");
    }
}
