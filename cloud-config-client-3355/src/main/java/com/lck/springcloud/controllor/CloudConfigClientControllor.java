package com.lck.springcloud.controllor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//动态刷新configClient
public class CloudConfigClientControllor {
    //这里是个坑 特别注意一下这里读取的是github上bootstrap配置文件
    @Value("${config.lck.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
