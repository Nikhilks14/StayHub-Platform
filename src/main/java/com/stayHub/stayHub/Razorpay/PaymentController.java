package com.stayHub.stayHub.Razorpay;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PaymentController {

    private RazorpayService razorpayService;

    public String createOrder(@RequestParam int amount, @RequestParam String currency){

        try{
            return razorpayService.createOrder(amount,currency , "receiptId_100");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
