package com.aloha.starmakers.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 검색 옵션
 * - keyword    : 검색어
 * - code       : 옵션 코드
 *   * 0 : 제목+내용
 *   * 1 : 제목
 *   * 2 : 내용
 *   * 3 : 작성자
 */
@AllArgsConstructor
@Data
public class Option {

    private String keyword; // 선언만 했을 때는 null
    private int code;
    private String codeName;

    // 홍보검색기능
    private boolean eventOngoing;
    private boolean eventExpired;
    private boolean eventUpcoming;
    private boolean instagram;
    private boolean youtube;
    private boolean chzzk;
    private boolean afreeca;
    private boolean food;
    private boolean travel;
    private boolean game;
    private boolean music;
    private boolean animal;
    private boolean workOut;
    private boolean asmr;
    private boolean fashion;

    public Option() {
        this.keyword = ""; // 빈 문자열
    }

    public Option(String codeName, int code) {
        this.codeName = codeName;
        this.code = code;
    }
}
