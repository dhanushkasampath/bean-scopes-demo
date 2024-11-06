package com.learn.bean_scopes_demo.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *  This example demonstrates how you can use a request-scoped bean to store and retrieve information specific
 *  to each HTTP request.
 *
 *  @Scope("request"): This annotation ensures that Spring creates a new instance of RequestScopedBean for
 *  each HTTP request. After the request is complete, the bean is discarded.
 *
 *  ---proxyMode = ScopedProxyMode.TARGET_CLASS ---
 * This will ensure that the request scoped bean is only created when there's an active HTTP request,
 * preventing the UnsatisfiedDependencyException.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class RequestScopedBean {

    private int counter = 0;

    public int getCounter() {
        return ++counter; // every time as the api response we get 1. because new bean is created at every time
    }

    // This postConstruct method will call at every time when requested.
    // That confirms this bean is created at everytime it is requested
    @PostConstruct
    public void init() {
        log.info("RequestScopedBean created with ID: " + System.currentTimeMillis());
    }
}