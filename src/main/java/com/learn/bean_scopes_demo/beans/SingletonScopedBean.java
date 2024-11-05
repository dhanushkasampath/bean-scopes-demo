package com.learn.bean_scopes_demo.beans;

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
public class SingletonScopedBean {

    private int counter = 0;

    public int incrementAndGet(){
        return ++counter;
    }
}
