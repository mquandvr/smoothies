package com.formos.smoothie.component.inventory;

import com.formos.smoothie.model.Inventory;

import java.util.List;

public interface InventoryService {

    List<Inventory> findAll();

    void update(Inventory invenUpdated);
}