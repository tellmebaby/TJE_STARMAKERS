package com.aloha.starmakers.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 검색 옵션
 * - keyword    : 검색어
 * - code       : 옵션 코드
 * * 0 : 제목+내용
 * * 1 : 제목
 * * 2 : 내용
 * * 3 : 작성자
 */
@AllArgsConstructor
@Data
public class Option {

    String keyword; // 선언만 했을 때는 null
    int code;

    public Option() {
        this.keyword = ""; // 빈 문자열
    }
    
}
