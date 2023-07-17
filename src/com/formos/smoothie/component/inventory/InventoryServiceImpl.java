package com.formos.smoothie.component.inventory;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Service;
import com.formos.smoothie.model.Inventory;
import com.formos.smoothie.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public void update(Inventory invenUpdated) {
        inventoryRepository.update(invenUpdated);
    }
}
