package com.formos.smoothie.component;

import com.formos.smoothie.common.ApplicationContext;

public interface ResourceService {

    void execute(ApplicationContext context, Object... args) throws Exception;
}
