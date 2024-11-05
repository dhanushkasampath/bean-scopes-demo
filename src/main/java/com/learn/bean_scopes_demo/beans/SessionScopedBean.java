package com.learn.bean_scopes_demo.beans;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * A session-scoped bean is created once per HTTP session and shared across all requests in that session.
 * This makes it useful for storing session-specific information, like a shopping cart or user preferences.
 *
 * 1. Enable HTTP Session in Spring Boot
 *
 * To use session-scoped beans, ensure that Spring Boot is set up to manage HTTP sessions.
 * Spring Boot does this by default, but you can explicitly enable it by adding @EnableWebMvc or
 * @EnableWebSecurity in the main application class if not already enabled.
 *
 * 2. Define a Session-Scoped Bean
 *
 * To create a session-scoped bean, annotate the bean class with @Scope("session"). This tells Spring to create
 * a new instance of this bean for each HTTP session and reuse it across multiple requests in the same session.
 *
 * Serializable: It’s a good practice to implement Serializable for session-scoped beans to allow serialization
 * if sessions need to be distributed across multiple servers.
 *
 * Session ID and Access Count: Here, the bean holds a session ID and an access counter that increments each
 * time the bean is accessed, allowing us to track usage within the session.
 *
 * With proxyMode = ScopedProxyMode.TARGET_CLASS, Spring will create a proxy for the SessionScopedBean,
 * which only resolves to an actual instance once there’s an active HTTP session. This approach allows you to
 * inject the session-scoped bean into singleton-scoped beans (like controllers) without triggering the
 * ScopeNotActiveException.
 *
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class SessionScopedBean implements Serializable {

    private String sessionId;
    private int accessCount;

    @PostConstruct
    public void init() {
        // Generate a unique session ID or use some unique attribute
        this.sessionId = "Session-" + System.currentTimeMillis();
        this.accessCount = 0;
        log.info("SessionScopedBean initialized with ID: {}", sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public int incrementAccessCount() {
        return ++accessCount;
    }
}