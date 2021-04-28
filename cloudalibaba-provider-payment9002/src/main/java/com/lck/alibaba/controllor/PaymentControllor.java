package com.lck.alibaba.controllor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentControllor {

//    @Value("${server.port}")
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(
            @PathVariable("id") Integer id
    ){
        return "nocos register ,serverPort "+ serverPort+" \t"+id;
    }
}
