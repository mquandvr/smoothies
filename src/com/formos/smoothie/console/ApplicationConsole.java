package com.formos.smoothie.console;

import com.formos.smoothie.common.ApplicationContext;
import com.formos.smoothie.component.CheckInventoryResource;
import com.formos.smoothie.component.InventoryResource;
import com.formos.smoothie.component.MenuResource;
import com.formos.smoothie.component.OrderResource;
import com.formos.smoothie.component.ReportResource;
import com.formos.smoothie.component.RevenueResource;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ApplicationConsole implements Runable {

    private static Scanner scanner;

    private static final String[] options = {"1- Inventory ",
            "2- Order",
            "3- Report",
            "4- Revenue Daily",
            "9- Exit",
    };

    private ApplicationContext context;

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run(Class<?> clazz) {
        scanner = new Scanner(System.in);
        context = new ApplicationContext(clazz);
        while (true){
            execute();
        }
    }

    private void execute() {
        int option;
        printMenu(options);
        try {
            option = scanner.nextInt();
            switch (option){
                case 1:
                    context.getInstance(InventoryResource.class).execute(context);
                    context.getInstance(CheckInventoryResource.class).execute(context);
                    break;
                case 2:
                    context.getInstance(MenuResource.class).execute(context, option);
                    option = scanner.nextInt();
                    context.getInstance(OrderResource.class).execute(context, option);
                    context.getInstance(CheckInventoryResource.class).execute(context);
                    break;
                case 3:
                    context.getInstance(ReportResource.class).execute(context, option);
                    break;
                case 4:
                    context.getInstance(RevenueResource.class).execute(context, option);
                    break;
                case 9: System.exit(0);
                    break;
                default:
                    System.out.println("Please enter an integer value between 1 and 9");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter an integer value between 1 and " + options.length);
            scanner.next();
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            scanner.next();
        }
    }

    public void printMenu(String[] options) {
        System.out.println("Enter your option: ");
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option: ");
    }
}
