package com.aloha.starmakers.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.mapper.MypageMappler;

@Service
public class MypageServiceImpl implements MypageService {

    @Autowired
    private MypageMappler mypageMappler;

    @Override
    public Users select(int userNo) throws Exception {

        Users user = mypageMappler.select(userNo);
        return user;
    }

    @Override
    public int update(Users user) throws Exception {

        int result = mypageMappler.update(user);
        return result;
    }

    @Override
    public int delete(int userNo) throws Exception {

        int result = mypageMappler.delete(userNo);
        return result;
    }

    
}