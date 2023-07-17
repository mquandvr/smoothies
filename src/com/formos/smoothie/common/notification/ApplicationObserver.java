package com.formos.smoothie.common.notification;

import java.util.ArrayList;
import java.util.List;

public abstract class ApplicationObserver implements Subject {

    protected List<IObserver> observerList;

    public ApplicationObserver() {
        observerList = new ArrayList<>();
    }

    @Override
    public void attach(IObserver observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    @Override
    public void detach(IObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notification() {
        for (IObserver observer : observerList) {
            execute(observer);
        }
    }

    public abstract void execute(IObserver observer);

}
