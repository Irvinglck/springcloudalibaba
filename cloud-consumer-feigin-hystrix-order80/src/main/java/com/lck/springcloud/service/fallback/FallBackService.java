package com.lck.springcloud.service.fallback;

import com.lck.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class FallBackService implements PaymentService {
    @Override
    public String testOk(Integer id) {
        return "testOk featacher hystrix fall back";
    }

    @Override
    public String testTimeOut(Integer id) {
        return "testTimeOut featacher hystrix fall back";
    }
}
