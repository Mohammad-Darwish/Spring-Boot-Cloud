package com.darwish.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // get method
    // http://localhost:8080/hello-world

    @GetMapping(path = "hello-world")
    public String helloWorld() {
        return "Hello World!";
    }
}
