package com.lck.cloud.alibaba.controllor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {


    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        return "------testB";
    }

    /**
     * 持久化配置
     * @return
     */
    @GetMapping("/rateLimit/byUrl")
    public String byUrl()
    {
        return "------持久化配置测试  rateLimit/byUrl";
    }
}
