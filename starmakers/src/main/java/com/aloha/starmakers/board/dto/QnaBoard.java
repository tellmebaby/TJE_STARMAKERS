package com.aloha.starmakers.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QnaBoard {
    private int qnaNo;
    private String qnaTitle;
    private String qnaWriter;
    private String qnaContent;
    private String qnaAnswer;
    private Date qnaRegDate;
    private Date qnaUpdDate;
    private int qnaViews;
    private int userNo;
    private String qnaStatus;  // 홍보요청 / 홍보중 / 홍보종료 / 반려
    
}