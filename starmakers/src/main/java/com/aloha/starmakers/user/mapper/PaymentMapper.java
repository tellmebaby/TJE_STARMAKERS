package com.aloha.starmakers.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.starmakers.user.dto.Payment;

@Mapper
public interface PaymentMapper {
    
    // 목록 조회
    public List<Payment> paymentList() throws Exception;

}
