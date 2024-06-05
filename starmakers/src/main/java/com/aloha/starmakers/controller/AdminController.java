package com.aloha.starmakers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.pay.dto.Pay;
import com.aloha.starmakers.pay.service.PayService;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PayService payService;
    
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
    
    
}
