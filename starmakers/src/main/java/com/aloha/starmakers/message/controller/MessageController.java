package com.aloha.starmakers.message.controller;

import java.util.List;

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

import com.aloha.starmakers.board.service.FileService;
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

    @Autowired
    private FileService fileService;

    @PostMapping("/insertToAdmin")
    public ResponseEntity<String> insertPro(@RequestBody Message messageDTO, HttpSession session) {

        String name = messageDTO.getName();
        int userNo = 0;

        if (name != "관리자" && name == null) {
            Users user = (Users) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(401).body("User not authenticated");
            }

            if (messageDTO.getContent() == null || messageDTO.getContent().isEmpty()) {
                return ResponseEntity.status(400).body("Content cannot be null or empty");
            }

            userNo = user.getUserNo();
        } else {
            userNo = messageDTO.getUserNo();
        }

        if (userNo == 0) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setCode(messageDTO.getCode());
        message.setPayNo(0);
        message.setQnaNo(0);
        message.setReplyNo(0);
        message.setUserNo(userNo);

        int result = messageService.insertMessage(message);
        if (result > 0) {
            log.info("Insert successful!");
            return ResponseEntity.ok("Message saved successfully");
        }
        return ResponseEntity.status(500).body("Failed to save message");
    }

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

    @GetMapping("/getMessagesByUser")
    public ResponseEntity<List<Message>> getMessagesByUser(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        int userNo = user.getUserNo();
        List<Message> messages = messageService.getMessageByUser(userNo);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/MessagesList")
    public ResponseEntity<List<Message>> messagesList() throws Exception {
        List<Message> messagesList = messageService.getMessagesList();
        for (Message message : messagesList) {
            Integer fileNo = fileService.profileSelect(message.getUserNo());
            message.setImgNo(fileNo);
        }
        return ResponseEntity.ok(messagesList);
    }

    @PostMapping("/getMessagesByUser")
    public ResponseEntity<List<Message>> userChatList(int no) throws Exception {
        List<Message> messagesList = messageService.getMessageByUser(no);
        messageService.updateView(no, "toAdmin");

        for (Message message : messagesList) {
            Integer fileNo = fileService.profileSelect(message.getUserNo());
            message.setImgNo(fileNo);
        }
        return ResponseEntity.ok(messagesList);
    }

}
