package com.aloha.starmakers.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.starmakers.pay.dto.Pay;

@Service
public class PayServiceImpl implements PayService {

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
        return userList(userNo);
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
