package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.order.OrderController;
import com.formos.smoothie.model.Order;
import com.formos.smoothie.utils.CommonUtil;

public class OrderResource implements ResourceService {

    @Override
    public void execute(ApplicationContext context, Object... args) throws Exception {
        OrderController orderController = context.getInstance(OrderController.class);
        int numOfCup = 1;
        Order order = orderController.orderSmoothie((Integer) args[0], numOfCup);
        if (order != null) {
            System.out.println(String.format("Order: %s - %s", order.getName(), CommonUtil.convertMoney(order.getTotalPrice())));
        }
    }
}
