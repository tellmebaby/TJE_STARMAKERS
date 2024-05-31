package com.aloha.starmakers.board.service;

public interface LikeService {
    
    // 좋아요하기
    public boolean toggleLike(int userNo, int starNo) throws Exception;
}
