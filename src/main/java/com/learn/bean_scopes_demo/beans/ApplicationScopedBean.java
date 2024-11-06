package com.learn.bean_scopes_demo.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * An application-scoped bean in Spring Boot has a single instance per web application context.
 * It is similar to the singleton scope but specifically within the context of a web application,
 * where it’s shared across all sessions and requests.
 *
 * Here’s an example of how to implement an application-scoped bean in Spring Boot.
 *
 * In this example, ApplicationScopedBean has a globalCounter field that is incremented every time it’s accessed.
 * Since this is an application-scoped bean, the globalCounter will persist and be shared across all requests and sessions.
 */
@Component
@Scope("application")
@Slf4j
public class ApplicationScopedBean {

    private int counter = 0;

    public int incrementGlobalCounter() {
        return ++counter;
    }

    @PostConstruct
    public void init() {
        log.info("ApplicationScopedBean created with ID: " + System.currentTimeMillis());
    }
}