package com.formos.smoothie.common;

import com.formos.smoothie.common.annotation.Bean;
import com.formos.smoothie.common.annotation.Configuration;
import com.formos.smoothie.common.notification.ApplicationObserver;
import com.formos.smoothie.component.notification.InventoryNotification;
import com.formos.smoothie.component.notification.InventoryObserver;

@Configuration
public class ApplicationConfig {

    @Bean
    public ApplicationObserver getInventoryObserver() {
        ApplicationObserver observer = new InventoryObserver();
        observer.attach(new InventoryNotification());

        return observer;
    }
}
