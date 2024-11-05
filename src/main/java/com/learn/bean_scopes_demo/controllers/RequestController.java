package com.learn.bean_scopes_demo.controllers;

import com.learn.bean_scopes_demo.beans.RequestScopedBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final RequestScopedBean requestScopedBean;

    public RequestController(RequestScopedBean requestScopedBean) {
        this.requestScopedBean = requestScopedBean;
    }

    @GetMapping("/request")
    public String getRequestId() {
        // This will return a unique request ID for each HTTP request
        return "Request ID: " + requestScopedBean.getRequestId();
    }
}

/**

 note: Every time when we make HTTP request, it will return the current time in millis.
 That means new object of  'RequestScopedBean' is created at every HTTP request

 http://localhost:8081/api/request
 Request ID: REQ-1730793386814

 This setup ensures that each request has its own instance of RequestScopedBean, making it suitable for
 request-specific data handling.

 **/