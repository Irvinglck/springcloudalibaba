package com.lck.springcloud.controllor;

import com.lck.springcloud.entities.CommonsResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerOrderControllor {


    private String INVOKE_PROVIDER="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/order/consul")
    public String getMst(){
        return restTemplate.getForObject(INVOKE_PROVIDER+"/payment/consul", String.class);
    }

}
