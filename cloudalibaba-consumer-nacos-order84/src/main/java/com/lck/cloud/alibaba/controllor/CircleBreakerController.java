package com.lck.cloud.alibaba.controllor;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lck.springcloud.entities.CommonsResult;
import com.lck.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {


    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")

//    @SentinelResource(value ="fallback", fallback = "handlerFallback") //    fallback管理异常处理
//    @SentinelResource(value ="fallback", blockHandler = "blockHandler") //    blocKHandler负责在sentinel里面配置的降级限流
    @SentinelResource(value ="fallback", blockHandler = "blockHandler", fallback = "handlerFallback") //二者皆有的时候，sentinel里面的规则优先
    public CommonsResult<Payment> fallback(@PathVariable Long id)
    {
        CommonsResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payMent/"+id,CommonsResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    public CommonsResult handlerFallback(@PathVariable("id") Long id,Throwable throwable){
        Payment payment=new Payment(id,null);
        return new CommonsResult(444,"fackback 无此流水，exception "+ throwable.getMessage(),payment);

    }
    public CommonsResult blockHandler(@PathVariable("id") Long id, BlockException e){
        Payment payment=new Payment(id,null);
        return new CommonsResult(5555,"blockHandler sentinel规则出发，exception "+ e.getMessage(),payment);

    }
}
