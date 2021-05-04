package com.lck.cloud.alibaba.controllor;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lck.cloud.alibaba.custom.CustomBlockHandler;
import com.lck.springcloud.entities.CommonsResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotKeyControllor {

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")
    public String testHotKey(
            @RequestParam(name = "p1", required = false) String p1,
            @RequestParam(name = "p2", required = false) String p2
    ) {
        return "------testHotKey 参数: " + p1 + " :: " + p2;

    }

    public String dealHandler_testHotKey(String p1, String p2, BlockException exception) {
        return "服务熔断以后降级方法--------dealHandler_testHotKey";
    }


    /**
     * 自定义通用的限流处理逻辑，
     blockHandlerClass = CustomerBlockHandler.class
     blockHandler = handleException2
     上述配置：找CustomerBlockHandler类里的handleException2方法进行兜底处理
     */
    /**
     * 自定义通用的限流处理逻辑
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handleException"
    )
    public CommonsResult customerBlockHandler() {
        return new CommonsResult(200,"按客户自定义限流处理逻辑");
    }
}
