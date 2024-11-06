package com.learn.bean_scopes_demo.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * To define a singleton-scoped bean, simply annotate the class with @Component (or @Service,
 * @Repository, etc.). No @Scope annotation is needed because singleton is the default scope.
 *
 * This means only one instance of the bean will be created and shared across the entire
 * application context.
 *
 * In this example, the SingletonScopedBean class has a counter field, which increments every
 * time incrementAndGet() is called. Since it is a singleton, this counter will persist across
 * the entire application context.
 */
@Component
@Slf4j
public class SingletonScopedBean {

    private int counter = 0;

    public int incrementAndGet(){
        return ++counter; // every time as the api response we get incremented value of previous one.
        // because no new bean is created at every time.
    }

    // This postConstruct method will call only at application start up. Not at every request
    @PostConstruct
    public void init() {
        log.info("SingletonScopedBean created with ID: " + System.currentTimeMillis());
    }
}
