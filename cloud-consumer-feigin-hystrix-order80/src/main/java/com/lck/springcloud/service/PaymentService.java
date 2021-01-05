package com.lck.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentService {
    @GetMapping("/payment/testOk/{id}")
    String testOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/testTimeOut/{id}")
    String testTimeOut(@PathVariable("id") Integer id);
}
