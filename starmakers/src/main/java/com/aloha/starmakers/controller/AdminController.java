package com.aloha.starmakers.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.board.service.FileService;
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
        return "/admin/pages/mailboxEvent";
    }
    @GetMapping("/pages/profile")
    public String userProfile(@RequestParam("userNo") int userNo,
                               Model model) throws Exception {
        // user 정보 가져오기
        Users user = userService.selectUserNo(userNo);
        model.addAttribute("user", user);
        
        // 프로필 이미지 가져오기
        int fileNo = fileService.profileSelect(userNo);
        model.addAttribute("fileNo", fileNo);

        return "/admin/pages/profile";
    }
    
    
    
}
