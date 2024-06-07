package com.aloha.starmakers.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.QnaBoard;
import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.board.service.QnaService;
import com.aloha.starmakers.board.service.StarService;
import com.aloha.starmakers.pay.service.PayService;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;




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
        Users user = new Users();
        int userNo = user.getUserNo();
        
        // int pay = payService.totalPrice(userNo);

        model.addAttribute("userList", userList);
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

        log.info("starList : " + starList);
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

        log.info("starList : " + starList);
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

        log.info("starList : " + starList);
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

        log.info("starList : " + starList);
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

        log.info("starList : " + starList);
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
    public String userProfile(@RequestParam("userNo") int userNo,
                               Model model) throws Exception {
        Users user = userService.selectUserNo(userNo);
        model.addAttribute("user", user);
        return "/admin/pages/profile";
    }

    @PostMapping("/pages/mailbox/allDelete")
    public String allDelete(@RequestParam("starNos") String starNos, @RequestParam("page") String page) throws Exception {
       
        int result = 0;


        result = starService.delete(starNos);

        if (result > 0) {
            return "redirect:/admin/pages/" + page;
        } 
        return "redirect:/admin/pages/" + page;  // 삭제 실패시에도 같은 페이지로 리디렉션
    }
    
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
