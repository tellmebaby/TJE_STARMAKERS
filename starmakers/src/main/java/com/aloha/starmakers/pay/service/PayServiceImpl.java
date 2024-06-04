package com.aloha.starmakers.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.pay.dto.Pay;
import com.aloha.starmakers.pay.mapper.PayMapper;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public int insert(Pay pay) {
        return insert(pay);
    }

    @Override
    public Pay select(int payNo) {
        return select(payNo);
    }

    @Override
    public List<Pay> userList(int userNo) {

        List<Pay> payList = payMapper.userList(userNo);
        return payList;

    }

    @Override
    public List<Pay> totalList() {
        return totalList();
    }

    @Override
    public int update(Pay pay) {
        return update(pay);
    }
    
}
