package com.lck.springcloud.service;

import com.lck.springcloud.service.fallback.FallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = FallBackService.class)
public interface PaymentService {
    @GetMapping("/payment/testOk/{id}")
    String testOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/testTimeOut/{id}")
    String testTimeOut(@PathVariable("id") Integer id);
}
