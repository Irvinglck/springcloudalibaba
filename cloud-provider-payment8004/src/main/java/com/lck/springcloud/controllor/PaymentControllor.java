package com.lck.springcloud.controllor;

import com.lck.springcloud.entities.CommonsResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentControllor {

    @Value("${server.port}")
    private String SERVER_PORT;

    @GetMapping("/payment/get")
    public CommonsResult getMsg(){

        return new CommonsResult().setMessage("springcloud整合zookeeper"+"\t"+ UUID.randomUUID()).setData(SERVER_PORT);
    }
}
