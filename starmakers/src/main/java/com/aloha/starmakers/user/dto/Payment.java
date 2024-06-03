package com.aloha.starmakers.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Payment {

    private int payNo;              // 결제데이터번호
    private int code;               // 상품코드
    private String price;           // 결제가격
    private String productTitle;    // 결제상품
    private int userNo;             // 유저데이터번호
    private Date regDate;           // 결제일자
    private String status;          // 상태
    
    /* 도희 천재 */
}
