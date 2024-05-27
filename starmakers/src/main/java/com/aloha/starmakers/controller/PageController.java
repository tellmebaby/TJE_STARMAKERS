package com.aloha.starmakers.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.user.dto.CustomUser;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;

import lombok.extern.slf4j.Slf4j;





@Slf4j
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private UserService userService;
    
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
    public String profile(Model model
                        ,Principal principal) throws Exception {
        log.info("principal : " + principal);

        String email = principal.getName();
        log.info("email : " + email);
        Users user = userService.select(email);
        log.info("::::::::::: user : " + user);
        model.addAttribute("user", user);

        return "/page/mypage/profile";
    }


    @GetMapping("/mypage/profileUpdate")
    public String update(Model model
                        ,Principal principal) throws Exception {
        
        String email = principal.getName();
        Users user = userService.select(email);
        log.info("::::::::::: user : " + email);
        model.addAttribute("user", user);
        return "/page/mypage/profileUpdate";
    }

    @PostMapping("/mypage/profileUpdate")
    public String updatePro( Users user ) throws Exception {
        int result = userService.update(user);
        log.info("수정 : " + user);
        if(result > 0){
            log.info("수정성공");
            return "redirect:/page/mypage/profile";
        }
        log.info("수정 실패");
        return "redirect:/page/mypage/profileUpdate?error";
    }

}