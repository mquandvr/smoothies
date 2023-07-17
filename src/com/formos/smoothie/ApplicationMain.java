package com.formos.smoothie;

import com.formos.smoothie.common.annotation.ComponentScan;
import com.formos.smoothie.console.ApplicationFactory;
import com.formos.smoothie.console.RunableType;

@ComponentScan("com.formos.smoothie")
public class ApplicationMain {

    public static void main(String[] args) {
        ApplicationFactory.getRunable(RunableType.SCANNER)
                .run(ApplicationMain.class);
    }

}
