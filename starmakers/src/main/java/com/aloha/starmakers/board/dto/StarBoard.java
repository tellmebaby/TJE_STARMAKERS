package com.aloha.starmakers.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StarBoard {
    private int starNo;
    private String starTitle;
    private String starWriter;
    private String starContent;
    private Date starRegDate;
    private Date starUpdDate;
    private int starViews;
    private int userNo;
    private int payNo;
    private int starLikes;
    private String starStatus;  // 홍보요청 / 홍보중 / 홍보종료 / 반려
    private String starCategory1; // 채널(인스타, 유튜브, 치지직 등)
    private String starCategory2; // 분야(음식, 여행, 게임 등)
    private String starType; // 유료 / 무료 / 디자인의뢰
    
}