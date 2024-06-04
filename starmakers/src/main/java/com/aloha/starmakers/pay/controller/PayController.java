package com.aloha.starmakers.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {

    // 테스트 페이지
    @GetMapping("/payments/checkout")
    public String checkout() {
        return "/page/payments/checkout";
    }

    @GetMapping("/payments/success")
    public String showSuccessPage() {
        return "/page/payments/success";
    }
    
    @ResponseBody
    @PostMapping("/payments/success")
    public String handleSuccess(
            @RequestParam("paymentKey") String paymentKey,
            @RequestParam("orderId") String orderId,
            @RequestParam("amount") int amount,
            @RequestParam("boardNo") int boardNo,
            @RequestParam("userNo") int userNo) {

        // 여기서 비즈니스 로직 처리 (예: 결제 검증, 데이터베이스 저장 등)
        System.out.println("Payment Key: " + paymentKey);
        System.out.println("Order ID: " + orderId);
        System.out.println("Amount: " + amount);
        System.out.println("Board No: " + boardNo);
        System.out.println("User No: " + userNo);

        // 처리 후 클라이언트에게 응답
        return "Payment success processed";
    }

    @GetMapping("/payments/fail")
    public String fail() {
        // 실패란 있을 수 없다!! 무조건 결제하라
        return "/page/payments/fail";
    }
}
