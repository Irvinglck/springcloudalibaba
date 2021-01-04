package com.lck.springcloud.service.impl;

import com.lck.springcloud.service.PaymentService;
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
}
