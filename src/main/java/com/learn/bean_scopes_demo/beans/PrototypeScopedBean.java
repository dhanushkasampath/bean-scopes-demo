package com.learn.bean_scopes_demo.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Prototype-scoped beans are created as new instances each time they are requested(I assume this is not http request) from the
 * Spring container, making them useful for managing state that should not be shared across
 * requests.
 *
 * In this example, the PrototypeScopedBean class has a unique instanceId that is set during bean initialization.
 * Every time the bean is requested, a new instance with a unique ID is created.
 *
 * The @Scope("prototype") annotation ensures that a new instance of PrototypeScopedBean is created every
 * time it’s requested from the #### Spring context ####.
 *
 * This setup is useful when you need a fresh instance of a bean for each request or operation,
 * such as for objects that handle request-specific data, temporary calculations, or processing-specific tasks.
 *
 */
@Component
@Scope("prototype")
@Slf4j
public class PrototypeScopedBean {

    private int counter = 0;

    public int getCounter() {
        return ++counter; // every time as the api response we get 1. because new bean is created at every time
    }

    // This postConstruct method will call at every time when requested.
    // That confirms this bean is created at everytime it is requested
    @PostConstruct
    public void init() {
        log.info("PrototypeScopedBean created with ID: {}", System.currentTimeMillis());
    }
}
