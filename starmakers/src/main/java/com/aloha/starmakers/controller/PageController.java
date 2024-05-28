package com.aloha.starmakers.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.starmakers.board.dto.QnaBoard;
import com.aloha.starmakers.board.service.QnaService;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;

import lombok.extern.slf4j.Slf4j;






@Slf4j
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private QnaService qnaService;
    
    @GetMapping("/mypage/profile")
    // public String read(@RequestParam("userNo") int userNo, Model model) throws Exception {
    public String read(Principal principal
                      ,HttpSession session
                      , Model model) throws Exception {
        // Princiapl 로 유저 가져오기
        // CustomUser loginUser = (CustomUser) principal;
        // int userNo = loginUser.getUser().getUserNo();
        // session 으로 가져오기
        Users user = (Users) session.getAttribute("user");
        String email = user.getEmail();
        log.info("email : " + email);
        user = userService.read(email);
        model.addAttribute("user", user);
        return "page/mypage/profile";
    }

    @GetMapping("/mypage/profileUpdate")
    // public String read(@RequestParam("userNo") int userNo, Model model) throws Exception {
    public String update(Principal principal
                      ,HttpSession session
                      , Model model) throws Exception {
        // Princiapl 로 유저 가져오기
        // CustomUser loginUser = (CustomUser) principal;
        // int userNo = loginUser.getUser().getUserNo();
        // session 으로 가져오기
        Users user = (Users) session.getAttribute("user");
        String email = user.getEmail();
        log.info("email : " + email);
        user = userService.read(email);
        model.addAttribute("user", user);
        return "page/mypage/profileUpdate";
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


    @GetMapping("/mypage/userDelete")
    public String delete(Principal principal
                      ,HttpSession session
                      , Model model) throws Exception {
        // Princiapl 로 유저 가져오기
        // CustomUser loginUser = (CustomUser) principal;
        // int userNo = loginUser.getUser().getUserNo();
        // session 으로 가져오기
        Users user = (Users) session.getAttribute("user");
        String email = user.getEmail();
        log.info("email : " + email);
        user = userService.read(email);
        log.info("user : " + user);
        model.addAttribute("user", user);
        return "page/mypage/userDelete";
    }

    @PostMapping("/mypage/userDelete")
    public String deletePro(Users user, HttpServletRequest request, HttpSession session) throws Exception {
        
        int result = userService.delete(user);
        log.info("번호 : " + user);
        if ( result > 0) {
            session.invalidate();
            log.info("번호 : " + user);
            return "redirect:/";
        }
        return "redirect:/page/mypage/userDelete?error";
    }

    /* ----------------------------------------------------------------------------- */
    /* 1:1 문의 */

    /**
     * 게시글 조회
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/mypage/inquiry")
    public String list(Model model) throws Exception {
        log.info("qna 목록");

        List<QnaBoard> qnaList = qnaService.list();
        model.addAttribute("qnaList", qnaList);
        return "/page/mypage/inquiry";
    }

    

}
