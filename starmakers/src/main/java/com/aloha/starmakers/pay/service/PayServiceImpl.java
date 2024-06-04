package com.aloha.starmakers.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.pay.dto.Pay;
import com.aloha.starmakers.pay.mapper.PayMapper;

@Service
public class PayServiceImpl implements PayService {


    @Autowired
    PayMapper payMapper;

    @Override
    public int insert(Pay pay) {
        payMapper.insert(pay);
        return pay.getPayNo();
    }

    @Override
    public Pay select(int starNo) {
        return payMapper.select(starNo);
    }

    @Override
    public List<Pay> userList(int userNo) {
        return payMapper.userList(userNo);
    }

    @Override
    public List<Pay> totalList() {
        return payMapper.totalList();
    }

    @Override
    public int update(Pay pay) {
        return payMapper.update(pay);
    }
    
}
