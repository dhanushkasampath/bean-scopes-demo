package com.learn.bean_scopes_demo.controllers;

import com.learn.bean_scopes_demo.beans.ApplicationScopedBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Now, inject this application-scoped bean into a controller. Because it’s application-scoped, the same instance
 * of ApplicationScopedBean will be used across all requests, sessions, and users.
 */
@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final ApplicationScopedBean applicationScopedBean;

    public ApplicationController(ApplicationScopedBean applicationScopedBean) {
        this.applicationScopedBean = applicationScopedBean;
    }

    @GetMapping("/application")
    public String getGlobalCounter() {
        int count = applicationScopedBean.incrementGlobalCounter();
        return "Application scope counter value: " + count;
    }
}
/**

 In this controller:

 Application-scoped Bean Injection: The ApplicationScopedBean is injected into the controller, and it retains its
 state across all requests and sessions.

 Global Counter: The incrementGlobalCounter() method is called each time /global-counter is accessed, increasing
 the count for every request.

 When you run the Spring Boot application and make multiple requests to the /api/global-counter endpoint,
 you’ll see that the globalCounter is incremented and shared across all requests, regardless of which session
 or user makes the request.

 Explanation
 Application Scope: The @Scope("application") annotation ensures that ApplicationScopedBean has a single instance
 for the entire application context, which is shared across all requests and sessions.

 Shared Data Across All Users: The globalCounter field retains its value across requests and sessions,
 making it useful for tracking global data, such as an application-wide counter.

 Thread Safety Considerations: Since application-scoped beans are shared across multiple requests and sessions,
 it’s important to make sure that any mutable state within the bean (like globalCounter) is either synchronized or
 managed in a thread-safe way to avoid concurrency issues in high-traffic applications.

 This setup is ideal for data that is relevant to the entire application rather than individual sessions or requests.
 Examples include application-wide counters, configurations, or caches that should be shared globally across all users.

 http://localhost:8081/api/global-counter
 Global counter value: 1

 */