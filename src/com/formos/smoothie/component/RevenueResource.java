package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.revenue.RevenueController;
import com.formos.smoothie.model.Revenue;
import com.formos.smoothie.utils.CommonUtil;

public class RevenueResource implements ResourceService {

    @Override
    public void execute(ApplicationContext context, Object... args) throws Exception {
        System.out.println("==================");
        RevenueController revenueController = context.getInstance(RevenueController.class);
        Revenue revenue = revenueController.getRevenue();
        String format = "%-20s%20s";
        System.out.println(String.format(format, "Product Capital", CommonUtil.convertMoney(revenue.getCapital())));
        System.out.println(String.format(format, "Product Cost", CommonUtil.convertMoney(revenue.getIngredientCost())));
        System.out.println(String.format(format, "Operating Costs", CommonUtil.convertMoney(revenue.getOperatingCosts())));
        System.out.println(String.format(format, "Total Sale Price", CommonUtil.convertMoney(revenue.getTotalSalePrice())));
        System.out.println(String.format(format, "Income", CommonUtil.convertMoney(revenue.getIncome())));
        System.out.println(String.format("%-10s%-20s", "Note: ", revenue.getMessage()));
        System.out.println("==================");
    }
}
