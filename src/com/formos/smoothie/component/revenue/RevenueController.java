package com.formos.smoothie.component.revenue;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Controller;
import com.formos.smoothie.model.Revenue;

@Controller
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    public Revenue getRevenue() {
        return revenueService.getRevenueDaily();
    }
}

