package com.lck.springcloud.controllor;


import com.lck.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixOrderControllor {
    @Resource
    private PaymentService paymentService;


    @GetMapping("/consumer/testOk/{id}")
    public String testOk(@PathVariable("id") Integer id) {
        return paymentService.testOk(id);
    }

    @GetMapping("/consumer/testTimeOut/{id}")
    public String testTimeOut(@PathVariable("id") Integer id){
        return paymentService.testTimeOut(id);
    }
}
