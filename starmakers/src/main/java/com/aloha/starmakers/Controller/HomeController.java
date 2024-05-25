package com.aloha.starmakers.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;


@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

   /**
     * 메인 화면
     * @param model
     * @param Principal
     * @return
     */
    @GetMapping({"", "/"})
    public String home(Principal principal) {
        log.info("메인 화면");
        log.info(":::::::::: principal ::::::::::");
        log.info("principal : " + principal);
        // Principal : 현재 로그인 한 사용자 정보를 확인하는 인터페이스
        return "/index";
    }
    

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    
    @GetMapping("/join")
    public String join() {
        return "/join";
    }

    @PostMapping("/join")
    public String joinPro( Users user ) throws Exception{
        
        int result = userService.join(user);
        if(result > 0){
            userService.login(user);
            return "redirect:/";
        }
        
        return "redirect:/join?error";
    }
    


    // @GetMapping("/{path}")
    // public String home(@PathVariable("path") String path) {        
    //     return path;
    // }

    // @GetMapping("/page/{path}")
    // public String user(@PathVariable("path") String path ) {        
    //     return "page/" + path;
    // } 

   
   
    

    
}
