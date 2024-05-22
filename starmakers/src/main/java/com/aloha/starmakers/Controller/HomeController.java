package com.aloha.starmakers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

   /**
     * 메인 화면
     * @param model
     * @param Principal
     * @return
     */
    @GetMapping({"", "/"})
    public String home() {
        // Principal : 현재 로그인 한 사용자 정보를 확인하는 인터페이스
        return "index";
    }
    

    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }
    
    // @GetMapping("/join")
    // public String join() {
    //     return "join";
    // }


    @GetMapping("/{path}")
    public String home(@PathVariable("path") String path) {        
        return path;
    }

    @GetMapping("/user/{path}")
    public String user(@PathVariable("path") String path ) {        
        return "user/" + path;
    } 

   
   
    

    
}
