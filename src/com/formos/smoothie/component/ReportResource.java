package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.report.ReportController;
import com.formos.smoothie.model.Order;
import com.formos.smoothie.utils.CommonUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

public class ReportResource implements ResourceService {

    @Override
    public void execute(ApplicationContext context, Object... args) throws Exception {
        System.out.println("call report");
        ReportController orderController = context.getInstance(ReportController.class);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<Order> orderList = orderController.getOrderByDate(LocalDate.now());
        System.out.println("===================");
        System.out.println(String.format("Report %s", formatter.format(localDate)));
        String formatRp = "%-5s%-50s%10s%15s";
        if (CommonUtil.isCollectionNotEmpty(orderList)) {
            System.out.println(String.format(formatRp, "No", "Name", "Quantity", "Price"));
            int no = 1;
            IntStream.range(0, orderList.size())
                    .forEach(idx -> System.out.println(String.format(
                            formatRp,
                            idx + 1,
                            orderList.get(idx).getName(),
                            orderList.get(idx).getNumOfCups(),
                            CommonUtil.convertMoney(orderList.get(idx).getTotalPrice())
                    )));
            System.out.println(String.format(formatRp, "", "", "", "Total"));
            long totalPrice = orderList.stream()
                    .mapToLong(order -> order.getTotalPrice())
                    .sum();
            long totalCups = orderList.stream()
                    .mapToLong(order -> order.getNumOfCups())
                    .sum();
            System.out.println(String.format(formatRp, "", "", totalCups, CommonUtil.convertMoney(totalPrice)));
        } else {
            System.out.println("Data not found");
        }
        System.out.println("===================");
    }
}
