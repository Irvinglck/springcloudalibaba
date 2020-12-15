package com.lck.springcloud.controllor;


import com.lck.springcloud.entities.CommonsResult;
import com.lck.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderControllor {

    private final static String PROVIDER_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonsResult getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PROVIDER_URL + "/payment/get/" + id, CommonsResult.class);
    }

    @GetMapping("/consumer/payment/create")
    public CommonsResult create(Payment payment) {
        return restTemplate.postForObject(PROVIDER_URL + "/payment/create",payment, CommonsResult.class);
    }
}
