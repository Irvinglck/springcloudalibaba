package com.lck.springcloud.controllor;


import com.lck.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback="falllBackCompensate_galable")
public class HystrixOrderControllor {
    @Resource
    private PaymentService paymentService;


    @GetMapping("/consumer/testOk/{id}")
    public String testOk(@PathVariable("id") Integer id) {
        return paymentService.testOk(id);
    }


//    @HystrixCommand(fallbackMethod = "falllBackCompensate",
//            commandProperties={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//                    value = "2000")})
    @GetMapping("/consumer/testTimeOut/{id}")
    @HystrixCommand
    public String testTimeOut(@PathVariable("id") Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentService.testTimeOut(id);
    }

    public String falllBackCompensate(@PathVariable("id") Integer id){
        return "hystrix消费方客户端系统繁忙,稍后重试"+id+"_______"+System.currentTimeMillis();
    }
    private String falllBackCompensate_galable(){
        return "galable___hystrix消费方客户端系统繁忙,稍后重试"+System.currentTimeMillis();
    }
}
