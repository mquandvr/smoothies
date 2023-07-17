package com.formos.smoothie.component.order;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Controller;
import com.formos.smoothie.model.Order;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    public List<Order> retrieveAllOrder() {
        return orderService.findAll();
    }

    public Order orderSmoothie(int type, int numOfCup) {
        return orderService.orderSmoothie(type, numOfCup);
    }
}
