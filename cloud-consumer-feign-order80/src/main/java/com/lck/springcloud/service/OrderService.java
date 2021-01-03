package com.lck.springcloud.service;

import com.lck.springcloud.entities.CommonsResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface OrderService {

    @GetMapping("/payment/get/{id}")
    CommonsResult getPayMent(@PathVariable("id") Long id);

    @GetMapping("/payment/testFeign/timeOut")
    CommonsResult timeOut();

}
