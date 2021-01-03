package com.lck.springcloud.controllor;


import com.lck.springcloud.entities.CommonsResult;
import com.lck.springcloud.entities.Payment;
import com.lck.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentControllor {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String SERVER_PORT;

    @GetMapping("/get/{id}")
    public CommonsResult getPayMent(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("根据id 查询结果 {} ", payment);
        return payment != null ? new CommonsResult<>(200, "返回成功 SERVER_PORT = "+SERVER_PORT, payment) :
                new CommonsResult<>(404, "没有查询到该id  " + id);
    }

    @PostMapping("/create")
    public CommonsResult createPayMent(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果 {} ", i);
        return i > 0 ? new CommonsResult<>(200, "插入成功 SERVER_PORT = "+SERVER_PORT, i) :
                new CommonsResult<>(500, "插入失败  " + i);
    }

    @GetMapping("/testFeign/timeOut")
    private CommonsResult testTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  new CommonsResult().setMessage("timeoutTest").setData(SERVER_PORT);
    }
}
