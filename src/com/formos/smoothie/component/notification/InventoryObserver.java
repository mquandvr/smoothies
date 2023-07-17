package com.formos.smoothie.component.notification;

import com.formos.smoothie.common.notification.ApplicationObserver;
import com.formos.smoothie.common.notification.IObserver;
import com.formos.smoothie.model.Recipe;

public class InventoryObserver extends ApplicationObserver {

    private Recipe recipe;

    @Override
    public void execute(IObserver observer) {
        ((InventoryIObserverService) observer).update(this.recipe);
    }

    public void save(Recipe recipe) {
        this.recipe = recipe;
        notification();
    }
}
