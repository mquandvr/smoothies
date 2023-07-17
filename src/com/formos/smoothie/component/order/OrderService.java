package com.formos.smoothie.component.order;

import com.formos.smoothie.model.Order;

import java.util.List;

public interface OrderService {

    Order orderSmoothie(int type, int numOfCup);

    List<Order> findAll();
}
