package com.learn.bean_scopes_demo.controllers;

import com.learn.bean_scopes_demo.beans.SessionScopedBean;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SessionController {

    private final SessionScopedBean sessionScopedBean;

    public SessionController(SessionScopedBean sessionScopedBean) {
        this.sessionScopedBean = sessionScopedBean;
    }

    @GetMapping("/session")
    public String getSessionInfo(HttpSession session) {
        int accessCount = sessionScopedBean.incrementAccessCount();
        return "Session ID: " + sessionScopedBean.getSessionId() + ", Access count: " + accessCount;
    }
}

/**

 In this controller:
 Session-scoped Bean Injection: The SessionScopedBean is injected and shared across all requests within the
 same HTTP session.

 Access Count: Each time /session-info is accessed, the accessCount is incremented, demonstrating that the bean
 is persistent across multiple requests in the same session.

 When you run the Spring Boot application and make requests to the /api/session-info endpoint within the same
 browser session, you’ll see that the sessionId and accessCount persist across requests. If you start a new session
 (for example, by opening a new incognito window), a new instance of the session-scoped bean is created.

 http://localhost:8081/api/session
 response ->
 Session ID: Session-1730795029290, Access count: 1

 refer the img: session-demo-for-different-browsers.png

 If you open a new session (e.g., an incognito window), the session ID will change, and the access count will start over from 1.

 Explanation
 ----------
 Session Scope: The @Scope("session") annotation ensures that a new SessionScopedBean is created for each HTTP
 session and reused across all requests within that session.

 Session Persistence: The accessCount and sessionId fields demonstrate that the bean’s state persists across
 requests within the same session.

 Session-specific Data: This setup is useful for storing data that is specific to a user’s session, like shopping
 cart data or user preferences, without exposing it across sessions.

 This approach makes it easy to maintain state within a user session while keeping data isolated from other users.

 */