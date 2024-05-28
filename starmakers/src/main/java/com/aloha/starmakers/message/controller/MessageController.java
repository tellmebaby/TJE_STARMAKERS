package com.aloha.starmakers.message.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.starmakers.message.dto.Message;
import com.aloha.starmakers.message.service.MessageService;
import com.aloha.starmakers.user.dto.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @PostMapping("/insertToAdmin")
    public ResponseEntity<String> insertPro(@RequestBody Message messageDTO, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        if (messageDTO.getContent() == null || messageDTO.getContent().isEmpty()) {
            return ResponseEntity.status(400).body("Content cannot be null or empty");
        }

        int userNo = user.getUserNo();
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setCode(messageDTO.getCode());
        message.setPayNo(0);
        message.setQnaNo(0);
        message.setReplyNo(0);
        message.setUserNo(userNo);

        int result = messageService.insertMessage(message);
        if(result > 0){
            log.info("Insert successful!");
            return ResponseEntity.ok("Message saved successfully");
        }
        return ResponseEntity.status(500).body("Failed to save message");
    }



    // @PostMapping("/insertToAdmin")
    // public String insertPro( Message message, HttpSession session) {
    //     Users user = (Users) session.getAttribute("user");
    //     int userNo = user.getUserNo();
    //     message.setPayNo(0);
    //     message.setQnaNo(0);
    //     message.setReplyNo(0);
    //     message.setUserNo(userNo);
    //     int result = messageService.insertMessage(message);
    //     if(result > 0){
    //         log.info("인설트 성공!!!!!!!!");
    //         return "redirect:/";
    //     }
    //     return "redirect:/";
    // }
    



    // @PostMapping("/")
    // @ResponseBody
    // public ResponseEntity<String> createMessage(@RequestBody Message message) {
    //     System.out.println("Received message content: " + message.getContent()); // 콘솔에 수신된 메시지 출력
    //     // 메시지 서비스를 통해 메시지를 저장
    //     messageService.insertMessage(message);
    //     System.out.println("Message saved successfully."); // 콘솔에 저장 완료 메시지 출력
    //     return ResponseEntity.ok("Message saved successfully");
    // }

    @GetMapping("/{messageNo}")
    public Message getMessage(@PathVariable int messageNo) {
        return messageService.getMessageById(messageNo);
    }

    @PutMapping("/")
    public void updateMessage(@RequestBody Message messageDTO) {
        messageService.updateMessage(messageDTO);
    }

    @DeleteMapping("/{messageNo}")
    public void deleteMessage(@PathVariable int messageNo) {
        messageService.deleteMessage(messageNo);
    }
}
