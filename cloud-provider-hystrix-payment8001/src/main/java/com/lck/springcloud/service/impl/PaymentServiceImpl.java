package com.lck.springcloud.service.impl;

import com.lck.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${server.port}")
    private String SERVER_PORT;

    @Override
    public String testOk(Integer id) {
        return "testOk 端口号 " + SERVER_PORT + "传入参数 " + id + "当前时间 " + System.currentTimeMillis();
    }

    @HystrixCommand(fallbackMethod = "falllBackCompensate",
            commandProperties={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "5000")})
    @Override
    public String testTimeOut(Integer id) {
        int number = 3;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "testTimeOk 端口号 " + SERVER_PORT + "传入参数 " + id + "当前时间 " + System.currentTimeMillis();
    }
    private String falllBackCompensate(Integer id){
        return "testTimeOk 端口号 " + SERVER_PORT + "传入参数 " + id + "当前时间 " + System.currentTimeMillis()+
                "Hystrix8001支付模块服务降级";
    }
}
