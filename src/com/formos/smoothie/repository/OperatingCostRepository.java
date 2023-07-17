package com.formos.smoothie.repository;

import com.formos.smoothie.model.OperatingCost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperatingCostRepository {

    private static List<OperatingCost> operatingCostList;

    static {
        operatingCostList = new ArrayList<>(
            Arrays.asList(new OperatingCost(1, "Rental Expense", 0),
                    new OperatingCost(2, "Salary", 0)
            )
        );
    }

    /**
     * SELECT SUM(COST) FROM OPERATING_COST
     * @return
     */
    public long getSumCost() {
        return operatingCostList.stream().mapToLong(OperatingCost::getCost).sum();
    }
}
