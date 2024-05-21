package com.aloha.starmakers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
    
}
