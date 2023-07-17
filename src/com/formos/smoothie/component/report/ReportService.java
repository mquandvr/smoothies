package com.formos.smoothie.component.report;

import com.formos.smoothie.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<Order> getOrderByDate(LocalDate localDate);
}
