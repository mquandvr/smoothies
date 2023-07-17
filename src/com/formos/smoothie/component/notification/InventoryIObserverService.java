package com.formos.smoothie.component.notification;

import com.formos.smoothie.common.notification.IObserver;
import com.formos.smoothie.model.Recipe;

public interface InventoryIObserverService extends IObserver {

    void update(Recipe recipe);
}
