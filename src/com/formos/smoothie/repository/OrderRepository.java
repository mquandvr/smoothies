package com.formos.smoothie.repository;

import com.formos.smoothie.model.Order;
import com.formos.smoothie.utils.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {

    private static List<Order> orderList;

    static {
        orderList = new ArrayList<>(
                Arrays.asList(new Order(1, "Strawberry Smoothie", 1, LocalDateTime.now().minusDays(1), 1, 50000),
                        new Order(2, "Banana Smoothie", 12, LocalDateTime.now().minusDays(10), 1, 60000)
                )
        );
    }

    /**
     * <pre> {@code
     * SELECT * FROM ORDER
     * }</pre>
     * @return
     */
    public List<Order> findAll() {
        return orderList;
    }

    /**
     * <pre> {@code
     * SELECT COUNT(*) FROM ORDER
     * }</pre>
     * @return
     */
    public int count() {
        return orderList.size();
    }

    /**
     * <pre> {@code
     * INSERT INTO ORDER VALUES(...)
     * }</pre>
     * @param order
     */
    public void insert(Order order) {
        orderList.add(order);
    }

    /**
     * <pre> {@code
     * UPDATE ORDER SET ? = ? WHERE ID = ?
     * }</pre>
     * @param orderUpdated
     */
    public void update(Order orderUpdated) {
        orderList = orderList.stream().map(order -> {
            if (order.getId() == orderUpdated.getId()) {
                order = orderUpdated;
            }
            return order;
        }).collect(Collectors.toList());
    }

    /**
     * <pre> {@code
     * SELECT * FROM ORDER WHERE ORDER_DATE = ?
     * }</pre>
     * @param localDate
     * @return
     */
    public List<Order> findByDate(LocalDate localDate) {
        return orderList.stream().filter(order -> DateTimeUtil.compareDate(order.getOrderDate().toLocalDate(), localDate))
                .collect(Collectors.toList());
    }

    /**
     * <pre> {@code
     * SELECT ID, NAME, MENU_ID, SUM(NUM_OF_CUPS), SUM(TOTAL_PRICE)
     * FROM ORDER
     * WHERE ORDER_DATE = ?
     * GROUP BY ID, NAME, MENU_ID
     * }</pre>
     * @param localDate
     * @return
     */
    public List<Order> findByDateAndGroup(LocalDate localDate) {
        return findByDate(localDate).stream()
                .collect(Collectors.groupingBy(Order::getMenuId))
                .entrySet().stream()
                .map(o -> o.getValue().stream()
                        .reduce((o1, o2) -> new Order(o1.getId(),
                                o1.getName(),
                                o1.getMenuId(),
                                null,
                                o1.getNumOfCups() + o2.getNumOfCups(),
                                o1.getTotalPrice() + o2.getTotalPrice()))
                )
                .map(m -> m.get())
                .collect(Collectors.toList());
    }
}
