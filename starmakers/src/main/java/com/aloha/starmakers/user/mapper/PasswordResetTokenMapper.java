package com.aloha.starmakers.user.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.aloha.starmakers.user.dto.PasswordResetToken;

@Mapper
public interface PasswordResetTokenMapper {
 
    public int save(PasswordResetToken token);

    public PasswordResetToken findByToken(String token);

    public int deleteByToken(String token);
}
