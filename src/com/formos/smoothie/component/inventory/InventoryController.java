package com.formos.smoothie.component.inventory;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Controller;
import com.formos.smoothie.model.Inventory;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    public List<Inventory> retrieveAllInventory() {
        return inventoryService.findAll();
    }

    public void update(Inventory invenUpdated) {
        inventoryService.update(invenUpdated);
    }
}
