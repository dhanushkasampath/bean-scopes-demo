1. Singleton
============

note: every time when we make this GET call, it will increment the count.
That means only single 'SingletonScopedBean' object has been created throughout the entire application

http://localhost:8081/api/singleton
Counter value: 3

Controller Injection: Injecting the singleton bean in the controller means the same
instance is used each time the controller handles a request.

2. prototype
============

note: every time when we make this GET call, it will return the current time in millis.
That means new object of  'PrototypeScopedBean' is created at every request

http://localhost:8081/api/prototype
Prototype instance ID: Instance-1730791153895

Unique Instance: The instanceId field allows us to track and verify that each request generates a new
instance of the prototype bean.

3. Request
==========

note: Every time when we make HTTP request, it will return the current time in millis.
That means new object of  'RequestScopedBean' is created at every HTTP request

http://localhost:8081/api/request
Request ID: REQ-1730793386814

This setup ensures that each request has its own instance of RequestScopedBean, making it suitable for
request-specific data handling.


4. Session
==========

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

5. Application
==============

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

6. WebSocket
============

Too hard to understand !!!!








****** Problems I had ******
1. What is the difference between Request and Prototype?
----------------------------------------------------

Prototype scope: new instance of PrototypeScopedBean is created every time it’s requested from the Spring context.
Request scope:  new instance of RequestScopedBean is created every time it’s requested  by HTTP request.

The difference I saw here is in prototype scope we used below 'ObjectFactory' to get the bean.

ie.

private final ObjectFactory<PrototypeScopedBean> prototypeBeanFactory;

// Then 'PrototypeScopedBean' is obtained by 'prototypeBeanFactory.getObject()' method;

    @GetMapping("/prototype")
    public String getPrototypeInstance() {
        // Each call to getObject() returns a new instance of PrototypeScopedBean
        PrototypeScopedBean prototypeBean = prototypeBeanFactory.getObject();
        return "Prototype instance ID: " + prototypeBean.getRequestId();
    }


2. What is the difference between Singleton and Application?
---------------------------------------------------------
Similar to singleton scope, in Application scope only one instance is created, but it’s tied specifically
to the lifecycle of the 'web application context' rather than the 'Spring IoC container'.

note: 'web application context' is on top of 'Spring IoC container'.



Endpoints exposed
http://localhost:8081/api/singleton
http://localhost:8081/api/application
http://localhost:8081/api/prototype
http://localhost:8081/api/request
http://localhost:8081/api/session