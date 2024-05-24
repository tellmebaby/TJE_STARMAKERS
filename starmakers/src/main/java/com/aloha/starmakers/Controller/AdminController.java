package com.aloha.starmakers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("")
    public String getMethodName() {
        return "/admin/index";
    }

    @GetMapping("/pages/{path}")
    public String getMethodName2(@PathVariable("path") String path) {        
        return "admin/pages/"+path;
    } 
}
