package com.lck.cloud.alibaba.custom;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lck.springcloud.entities.CommonsResult;

public class CustomBlockHandler {

    public static CommonsResult handleException(BlockException blockException){
        return  new CommonsResult(200,"自定义限流处理异常方法 [handleException]");
    }
}
