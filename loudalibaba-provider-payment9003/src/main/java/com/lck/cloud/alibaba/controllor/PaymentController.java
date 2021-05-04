package com.lck.cloud.alibaba.controllor;

import com.lck.springcloud.entities.CommonsResult;
import com.lck.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L, new Payment(2L, "bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L, new Payment(3L, "6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping("/payMent/{id}")
    public CommonsResult payMent(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonsResult<Payment> result = new CommonsResult(200, "from mysql,serverPort:  " + serPort, payment);
        return result;
    }
}
