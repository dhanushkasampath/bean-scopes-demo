package com.learn.bean_scopes_demo.controllers;

import com.learn.bean_scopes_demo.beans.SingletonScopedBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SingletonController {

    private final SingletonScopedBean singletonScopedBean;

    public SingletonController(SingletonScopedBean singletonScopedBean) {
        this.singletonScopedBean = singletonScopedBean;
    }

    @GetMapping("/singleton")
    public String getCounter() {
        // Increment the counter and return its current value
        int currentCount = singletonScopedBean.incrementAndGet();
        return "Singleton scope counter value: " + currentCount;
    }
}

/**

    note: every time when we make this GET call, it will increment the count.
    That means only single 'SingletonScopedBean' object has been created throughout the entire application

    http://localhost:8081/api/singleton
    Counter vale: 3

    Controller Injection: Injecting the singleton bean in the controller means the same
    instance is used each time the controller handles a request.

    Even though we use different browsers or incognito(these kinds of things refer to session), the value of counter
    variable does not change

**/

