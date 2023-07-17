package com.formos.smoothie.component.revenue;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Service;
import com.formos.smoothie.model.Inventory;
import com.formos.smoothie.model.Revenue;
import com.formos.smoothie.repository.InventoryRepository;
import com.formos.smoothie.repository.OperatingCostRepository;
import com.formos.smoothie.repository.OrderRepository;
import com.formos.smoothie.utils.CommonConstants;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OperatingCostRepository operatingCostRepository;

    @Override
    public Revenue getRevenueDaily() {
        LocalDate daily = LocalDate.now();
        long totalSalePrice = getTotalSale(daily);

        List<Inventory> inventoryList = inventoryRepository.findAll();
        long ingredientCost = getCost(inventoryList);
        long capital = getCapital(inventoryList);
        long operatingCost = getOperatingCost();
        long income = totalSalePrice - ingredientCost - operatingCost;
        String message = "";
        if (income > 0) {
            message = "Profit";
        } else if (income == 0) {
            message = "Break-Even";
        } else {
            message = "Loss";
        }

        Revenue revenue = new Revenue();
        revenue.setCapital(capital);
        revenue.setIngredientCost(ingredientCost);
        revenue.setIncome(income);
        revenue.setOperatingCosts(operatingCost);
        revenue.setTotalSalePrice(totalSalePrice);
        revenue.setMessage(message);

        return revenue;
    }

    private long getOperatingCost() {
        return operatingCostRepository.getSumCost() / CommonConstants.TOTAL_BUSINESS_DAY;
    }

    private long getCapital(List<Inventory> inventoryList) {
        return inventoryList.stream()
                .mapToLong(inventory -> inventory.getPrice())
                .sum();
    }

    private long getCost(List<Inventory> inventoryList) {
        return inventoryList.stream()
                .mapToLong(inventory -> inventory.getUnitPrice() * inventory.getQuantity())
                .sum();
    }

    private long getTotalSale(LocalDate localDate) {
        return orderRepository.findByDate(localDate).stream()
                .mapToLong(order -> order.getTotalPrice())
                .sum();
    }


}
