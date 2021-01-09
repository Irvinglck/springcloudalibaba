package com.lck.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
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




    //=====服务熔断
    @HystrixCommand(fallbackMethod = "testFusingFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    @Override
    public String testFusing(Integer id) {

        if(id<0){
            throw new RuntimeException("***********id 不能为负数");
        }
        String serialNum= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功服务流水号是***"+serialNum;
    }


    public String testFusingFallBack(Integer id){
        return "id 不可为负数****** "+id;
    }

    private String falllBackCompensate(Integer id){
        return "testTimeOk 端口号 " + SERVER_PORT + "传入参数 " + id + "当前时间 " + System.currentTimeMillis()+
                "Hystrix8001支付模块服务降级";
    }



}
