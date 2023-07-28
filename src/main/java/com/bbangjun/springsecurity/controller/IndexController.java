package com.bbangjun.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    // localhost:8081/
    // localhost:8080
    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }
}
