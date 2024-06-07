package com.aloha.starmakers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.QnaBoard;
import com.aloha.starmakers.board.dto.Reply;
import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.board.service.FileService;
import com.aloha.starmakers.board.service.QnaService;
import com.aloha.starmakers.board.service.ReplyService;
import com.aloha.starmakers.board.service.StarService;
import com.aloha.starmakers.pay.dto.Pay;
import com.aloha.starmakers.pay.service.PayService;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;

import lombok.extern.slf4j.Slf4j;





@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PayService payService;
    
    @Autowired
    private StarService starService;

    @Autowired
    private QnaService qnaService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private FileService fileService;
    
    @GetMapping("")
    public String getMethodName() {
        return "/admin/index";
    }

    @GetMapping("/pages/{path}")
    public String getMethodName2(@PathVariable("path") String path) {        
        return "admin/pages/"+path;
    } 

    @GetMapping("/pages/projects")
    public String userList(Model model) throws Exception {

        List<Users> userList = userService.list();
        
        
        // int pay = payService.totalPrice(userNo);

        model.addAttribute("userList", userList);
        // model.addAttribute("pay", pay);
        // model.addAttribute("pay", pay);

        return "/admin/pages/projects";
    }

    @GetMapping("/pages/mailbox")
    public String allList(String type, Model model, Page page,
            Option option) throws Exception {

        log.info(type);
        List<StarBoard> starList = starService.list(type, page, option);
        
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

        return "/admin/pages/mailbox";
    }
    
    @GetMapping("/pages/mailboxStar")
    public String StarList(@RequestParam(value = "type", defaultValue = "starCard") String type, Model model, Page page,
            Option option) throws Exception {

        log.info(type);
        List<StarBoard> starList = starService.list(type, page, option);
        
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

        return "/admin/pages/mailboxStar";
    }

    @GetMapping("/pages/mailboxEvent")
    public String eventList(@RequestParam(value = "type", defaultValue = "event") String type, Model model, Page page,
            Option option) throws Exception {

        log.info(type);
        List<StarBoard> starList = starService.list(type, page, option);
        
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

        return "/admin/pages/mailboxEvent";
    }

    @GetMapping("/pages/mailboxReview")
    public String reviewList(@RequestParam(value = "type", defaultValue = "review") String type, Model model, Page page,
            Option option) throws Exception {

        log.info(type);
        List<StarBoard> starList = starService.list(type, page, option);
        
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

        return "/admin/pages/mailboxReview";
    }

    @GetMapping("/pages/mailboxAn")
    public String anList(@RequestParam(value = "type", defaultValue = "an") String type, Model model, Page page,
            Option option) throws Exception {

        log.info(type);
        List<StarBoard> starList = starService.list(type, page, option);
        
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

        return "/admin/pages/mailboxAn";
    }

    @GetMapping("/pages/mailboxQna")
    public String qnaList(Model model, Page page,
            Option option) throws Exception {
        
        List<QnaBoard> qnaList = qnaService.list(page, option);
            
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

        return "/admin/pages/mailboxQna";
    }
    @GetMapping("/pages/profile")
    public String userProfile(@RequestParam("userNo") int userNo
                             ,Model model, Page page, Option option) throws Exception {
        // user 정보 가져오기
        Users user = userService.selectUserNo(userNo);
        model.addAttribute("user", user);
        
        // 프로필 이미지 가져오기
        int fileNo = fileService.profileSelect(userNo);
        model.addAttribute("fileNo", fileNo);

        // 결제 금액 가져오기
        Pay pay = payService.totalPrice(userNo);
        if ( pay != null ){
            int totalPrice = pay.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        } else {
            int totalPrice = 0;
            model.addAttribute("totalPrice", totalPrice);
        }

        // 작성글 정보 가져오기
        List<StarBoard> starBoard = starService.promotionList(userNo, page, option);
        int boardTotal = 0;
        if (starBoard != null && !starBoard.isEmpty()){
            boardTotal = starBoard.size();
        } else {
            boardTotal = 0;
        }
        model.addAttribute("boardTotal", boardTotal);

        // 작성 댓글 정보 가져오기
        List<Reply> replyList = replyService.selectUser(userNo);
        int replyTotal = 0;
        if(replyList != null && !replyList.isEmpty()){
            replyTotal = replyList.size();
            model.addAttribute("replyTotal", replyTotal);
        } else {
            model.addAttribute("replyTotal", replyTotal);
        }
        return "/admin/pages/profile";
    }

    @PostMapping("/pages/profileUpdate")
    public String profileUpdate(Users user) throws Exception {
            int result = userService.update(user);
            log.info("수정 : " + user);
            int userNo = user.getUserNo();
            if(result > 0){
                log.info("수정성공");
                return "redirect:/admin/pages/profile?userNo=" + userNo;
            }
            log.info("수정 실패");
            return "redirect:/admin/pages/profile?error";
    }
    
    // 전체 게시판 삭제
    @PostMapping("/pages/mailbox/allDelete")
    public String allDelete(@RequestParam("starNos") String starNos, @RequestParam("page") String page) throws Exception {
       
        int result = 0;


        result = starService.delete(starNos);

        if (result > 0) {
            return "redirect:/admin/pages/" + page;
        } 
        return "redirect:/admin/pages/" + page;  // 삭제 실패시에도 같은 페이지로 리디렉션
    }
    
    // Q&A 게시판 
    @PostMapping("/pages/mailbox/qnaDelete")
    public String qnaDelete(@RequestParam("qnaNos") String qnaNos) throws Exception {
       
        int result = 0;
        result = qnaService.delete(qnaNos);

        if (result > 0) {
            return "redirect:/admin/pages/mailboxQna";
        } 
        return "redirect:/admin/pages/mailboxQna";  // 삭제 실패시에도 같은 페이지로 리디렉션
    }
    
    
    
    
}
