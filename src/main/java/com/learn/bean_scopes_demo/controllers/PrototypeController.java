package com.learn.bean_scopes_demo.controllers;

import com.learn.bean_scopes_demo.beans.PrototypeScopedBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrototypeController {

    /**
     * Since Spring would typically use a singleton-scoped controller, we must use ObjectFactory or Provider to
     * get a new instance of the prototype bean each time it’s requested.
     */
    private final ObjectFactory<PrototypeScopedBean> prototypeBeanFactory;

    public PrototypeController(ObjectFactory<PrototypeScopedBean> prototypeBeanFactory) {
        this.prototypeBeanFactory = prototypeBeanFactory;
    }

    @GetMapping("/prototype")
    public String getPrototypeInstance() {
        // Each call to getObject() returns a new instance of PrototypeScopedBean
        PrototypeScopedBean prototypeBean = prototypeBeanFactory.getObject();
        return "Prototype scope counter value: " + prototypeBean.getCounter();
    }
}

/**

 note: every time when we make this GET call, it will return the current time in millis.
 That means new object of  'PrototypeScopedBean' is created at every request

 http://localhost:8081/api/prototype
 Prototype instance ID: Instance-1730791153895

 Unique Instance: The instanceId field allows us to track and verify that each request generates a new
 instance of the prototype bean.

 **/
