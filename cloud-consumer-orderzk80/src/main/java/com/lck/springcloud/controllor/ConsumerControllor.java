package com.lck.springcloud.controllor;

import com.lck.springcloud.entities.CommonsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerControllor {
    private final String invokeUrl="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/order/getZk")
    public Object getMst(){
        return restTemplate.getForEntity(invokeUrl+"/payment/get",CommonsResult.class);
    }
}
