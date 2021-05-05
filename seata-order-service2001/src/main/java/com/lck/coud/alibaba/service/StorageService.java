package com.lck.coud.alibaba.service;

import com.lck.coud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage-service")
public interface StorageService {

    /**
     * 扣减库存
     * feigen就是调用接口
     */
//    Long productId, Integer count)
    @PostMapping("/storage/decrease")
    CommonResult decrease(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "count") Integer count
    );
}
