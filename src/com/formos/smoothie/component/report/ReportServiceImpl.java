package com.formos.smoothie.component.report;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Service;
import com.formos.smoothie.model.Order;
import com.formos.smoothie.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrderByDate(LocalDate localDate) {
        return orderRepository.findByDateAndGroup(localDate);
    }
}
