package com.formos.smoothie.console;

public class ApplicationFactory {

    public static final Runable getRunable(RunableType runableType) {
        switch (runableType) {
            case SCANNER:
            default:
                return new ApplicationConsole();
        }
    }
}
