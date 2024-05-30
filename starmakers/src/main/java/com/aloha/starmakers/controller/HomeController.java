package com.aloha.starmakers.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;




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
    
    @GetMapping("/exception")
    public String exception(Authentication auth, Model model) {
        log.info("인증 예외 처리...");
        log.info("auth :" + auth.toString());
        model.addAttribute("msg","인증 거부 : "+auth.toString());
        return "/exception";
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

    
    @GetMapping("/page/introduce")
    public String introduce() {
        return "page/introduce";
    }
    


    // @GetMapping("/{path}")
    // public String home(@PathVariable("path") String path) {        
    //     return path;
    // }

    @GetMapping("/page/{path}")
    public String user(@PathVariable("path") String path ) {        
        return "page/" + path;
    } 

    @GetMapping("/page/starCard/{path}")
    public String starcard(@PathVariable("path") String path) {
        return "page/starCard/" + path;
    }
    
    @GetMapping("/page/mypage/{path}")
    public String mypage(@PathVariable("path") String path) {
        return "page/mypage/" + path;
    }
   
    @GetMapping("/page/board/{path}")
    public String board(@PathVariable("path") String path) {
        return "page/board/" + path;
    }
    
    @GetMapping("/page/board/eventBoard/{path}")
    public String eventBoard(@PathVariable("path") String path) {
        return "page/board/eventBoard/" + path;
    }
    
    @GetMapping("/page/board/anBoard/{path}")
    public String anBoard(@PathVariable("path") String path) {
        return "page/board/anBoard/" + path;
    }
    @GetMapping("/page/board/qnaBoard/{path}")
    public String qnaBoard(@PathVariable("path") String path) {
        return "page/board/qnaBoard/" + path;
    }
    @GetMapping("/page/board/reviewBoard/{path}")
    public String reviewBoard(@PathVariable("path") String path) {
        return "page/board/reviewBoard/" + path;
    }

    @GetMapping("/imgtest")
    public String test( Model model ) {
        int img = 2;
        model.addAttribute("img", img);
        return "/imgtest";
    }
    
}
