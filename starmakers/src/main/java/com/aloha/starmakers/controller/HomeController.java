package com.aloha.starmakers.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.user.dto.CustomUser;
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
    public String home(Principal principal, HttpSession session) {
        log.info("메인 화면");
        log.info(":::::::::: principal ::::::::::");
        log.info("principal : " + principal);
        log.info("user : " + session.getAttribute("user"));

        // Principal : 현재 로그인 한 사용자 정보를 확인하는 인터페이스
        return "index";
    }
    

    /**
     * 로그인 화면 이동
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    
    /**
     * 회원가입 화면 이동
     * @return
     */
    @GetMapping("/join")
    public String join() {
        return "/join";
    }

    /**
     * 회원가입처리
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/join")
    public String joinPro( Users user, HttpServletRequest request ) throws Exception{
        
        int result = userService.join(user);
        if(result > 0){
            userService.login(user, request);
            return "redirect:/";
        }
        
        return "redirect:/join?error";
    }
    

    /**
     * 가입여부확인
     * @param user
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/page/recoverId")
    public String postMethodName( Users user , Model model ) throws Exception{
        
        int result = userService.selectEmail(user);

        if( result > 0 ) {
            model.addAttribute("user", user);
            return "/page/recoverComplete";
        }
        
        return "redirect:/page/recoverId?error";
    }
    

    @PostMapping("/checkUserId")
    public ResponseEntity<Boolean> checkUserId(@RequestBody Users user) throws Exception {
        boolean isTaken = userService.selectId(user) > 0;
        return ResponseEntity.ok(isTaken);
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
