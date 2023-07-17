package com.formos.smoothie.component.report;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Controller;
import com.formos.smoothie.model.Order;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    public List<Order> getOrderByDate(LocalDate localDate) {
        return reportService.getOrderByDate(localDate);
    }
}
