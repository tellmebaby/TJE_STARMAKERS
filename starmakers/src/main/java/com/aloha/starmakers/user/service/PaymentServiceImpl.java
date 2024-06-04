package com.aloha.starmakers.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.user.dto.Payment;
import com.aloha.starmakers.user.mapper.PaymentMapper;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> paymentList() throws Exception {
        
        List<Payment> paymentList = paymentMapper.paymentList();
        return paymentList;
    }


    
}
