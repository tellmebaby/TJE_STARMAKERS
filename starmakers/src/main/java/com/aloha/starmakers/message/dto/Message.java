package com.aloha.starmakers.message.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Message {
    
    private int messageNo;
    private String content;
    private String code;
    private Date regDate;
    private int replyNo;
    private int payNo;
    private int qnaNo;
    private int userNo;
}
