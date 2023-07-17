package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.inventory.InventoryController;
import com.formos.smoothie.model.Inventory;
import com.formos.smoothie.utils.CommonUtil;

import java.util.List;

public class InventoryResource implements ResourceService {

    @Override
    public void execute(ApplicationContext context, Object... args) throws Exception {
        System.out.println("==================");
        InventoryController inventoryController = context.getInstance(InventoryController.class);
        List<Inventory> inventory = inventoryController.retrieveAllInventory();
        String formatRp = "%-30s%-10s%15s%15s";
        System.out.println(String.format(formatRp, "Name", "Quantity", "Price", "Unit Price"));
        inventory.forEach(invent -> {
            System.out.println(String.format(formatRp
                    , invent.getName()
                    , invent.getQuantity() + " " + invent.getUnit()
                    , CommonUtil.convertMoney(invent.getPrice())
                    , CommonUtil.convertMoney(invent.getUnitPrice())
            ));
        });
        System.out.println("==================");
    }
}
