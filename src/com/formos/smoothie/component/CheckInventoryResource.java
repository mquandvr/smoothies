package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.menu.MenuController;
import com.formos.smoothie.utils.CommonUtil;

import java.util.List;

public class CheckInventoryResource implements ResourceService {
    @Override
    public void execute(ApplicationContext context, Object... args) throws Exception {
        MenuController menuController = context.getInstance(MenuController.class);
        menuController.updatePrice();
        List<String> messageList = menuController.checkIngredient();
        if (CommonUtil.isCollectionNotEmpty(messageList)) {
            System.out.println("==================");
            messageList.forEach(System.out::println);
            System.out.println("==================");
        }
    }
}
