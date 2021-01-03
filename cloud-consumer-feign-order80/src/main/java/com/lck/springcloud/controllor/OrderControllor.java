package com.lck.springcloud.controllor;

import com.lck.springcloud.entities.CommonsResult;
import com.lck.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderControllor {

    @Resource
    private OrderService orderService;

    @GetMapping("/feignConsumer/get/{id}")
    public CommonsResult getPaymentById(@PathVariable("id") Long id){
        return orderService.getPayMent(id);
    }

    @GetMapping("/feiTimeOut")
    public CommonsResult getPaymentById(){
        return orderService.timeOut();
    }
}
