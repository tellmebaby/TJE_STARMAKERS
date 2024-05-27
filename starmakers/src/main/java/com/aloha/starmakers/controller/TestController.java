package com.aloha.starmakers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {
    

    @GetMapping("/test")
    public String test() {
        log.info("/test/test");
        return "/test/test";
    }
    
    
}
