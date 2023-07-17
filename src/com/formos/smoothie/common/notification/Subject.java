package com.formos.smoothie.common.notification;

public interface Subject {

    void attach(IObserver observer);
    void detach(IObserver observer);
    void notification();
}
