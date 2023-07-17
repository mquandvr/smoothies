package com.formos.smoothie.repository;

import com.formos.smoothie.model.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryRepository {

    private static List<Inventory> inventoryList;

    static {
        inventoryList = new ArrayList<>(
                Arrays.asList(new Inventory(1001, "Strawberry Fruit", 3000, 170000, "g", 57),
                        new Inventory(2001, "Ice", 5000, 250000, "ml", 50),
                        new Inventory(2002, "Condensed milk", 1000, 50000, "ml", 50),
                        new Inventory(2003, "Sugar", 1000, 15000, "g", 15),
                        new Inventory(1002, "Banana Fruit", 1000, 25000, "g", 25),
                        new Inventory(1003, "Mango Fruit", 1000, 50000, "g", 50)
                )
        );
    }

    /**
     * SELECT * FROM INVENTORY ORDER BY ID
     * @return
     */
    public List<Inventory> findAll() {
        return inventoryList.stream().sorted(Comparator.comparing(Inventory::getId)).collect(Collectors.toList());
    }

    /**
     * SELECT * FROM INVENTORY WHERE ID = ? ORDER BY ID
     * @return
     */
    public Inventory findById(int inventoryId) {
        return inventoryList.stream()
                .filter(i -> i.getId() == inventoryId)
                .findFirst().get();
    }

    /**
     * UPDATE INVENTORY SET ? = ? WHERE ID = ?
     * @return
     */
    public void update(Inventory invenUpdated) {
        inventoryList.remove(invenUpdated);
        inventoryList.add(invenUpdated);
    }

}
