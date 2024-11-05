package com.learn.bean_scopes_demo.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Prototype-scoped beans are created as new instances each time they are requested from the
 * Spring container, making them useful for managing state that should not be shared across
 * requests.
 *
 * In this example, the PrototypeScopedBean class has a unique instanceId that is set during bean initialization.
 * Every time the bean is requested, a new instance with a unique ID is created.
 *
 * The @Scope("prototype") annotation ensures that a new instance of PrototypeScopedBean is created every
 * time it’s requested from the Spring context.
 *
 * This setup is useful when you need a fresh instance of a bean for each request or operation,
 * such as for objects that handle request-specific data, temporary calculations, or processing-specific tasks.
 *
 */
@Component
@Scope("prototype")
@Slf4j
public class PrototypeScopedBean {

    private String instanceId;

    @PostConstruct
    public void init() {
        // Assign a unique instance ID (e.g., timestamp-based) to track this instance
        this.instanceId = "Instance-" + System.currentTimeMillis();
        log.info("PrototypeScopedBean created with ID: {}", instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }
}
