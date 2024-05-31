package com.aloha.starmakers.user.dto;

import lombok.Data;

@Data
public class Payment {

    private int payNo;          // 결제데이터번호
    private int code;           // 상품코드
    private String price;       // 결제가격
    private int userNo;         // 유저데이터번호
    private int productNo;      // 상품데이터번호
    
}
