package com.aloha.starmakers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.MypageService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;





@Slf4j
@Controller
@RequestMapping("/page")
public class PageController {
    
    @Autowired
    private MypageService mypageService;
    
    @GetMapping("/{path}")
    public String page(@PathVariable("path") String path) {
        log.info("/page");
        return "page/"+path;
    } 

    @GetMapping("/board/{path1}/{path2}")
    public String board(@PathVariable("path1") String path1,@PathVariable("path2") String path2) {
        return "page/board/"+path1+"/"+path2;
    }

    @GetMapping("/mypage/{path}")
    public String mypage(@PathVariable("path") String path) {
        return "page/mypage/"+path;
    }

    @GetMapping("/starCard/{path}")
    public String starCard(@PathVariable("path") String path) {
        return "page/starCard/"+path;
    }
    
        @GetMapping("/mypage/profile")
        public String read(@RequestParam("userNo") int userNo, Model model) throws Exception {

            Users user = mypageService.select(userNo);
            model.addAttribute("user", user);
            return "page/mypage/profile";
        }
    

}
