package com.aloha.starmakers.pay.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Pay {
    private int payNo;
    private String code;
    private int price;
    private int user_no;
    private int star_no;
    private Date reg_date;
    private String status;
}
