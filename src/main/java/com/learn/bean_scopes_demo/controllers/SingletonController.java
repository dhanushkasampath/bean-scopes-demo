package com.learn.bean_scopes_demo.controllers;

import com.learn.bean_scopes_demo.beans.SingletonScopedBean;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SingletonController {

    private final SingletonScopedBean singletonScopedBean;

    @GetMapping("/counter")
    public String getCounter(){
        // Increment the counter and return its current value
        int currentCount = singletonScopedBean.incrementAndGet();
        return "Counter vale: " + currentCount;
    }

/**

    note: every time when we make this GET call, it will increment the count.
    That means only single 'SingletonScopedBean' object has been created throughout the entire application

    http://localhost:8081/api/counter
    Counter vale: 3

    Controller Injection: Injecting the singleton bean in the controller means the same
    instance is used each time the controller handles a request.

**/

}
