package com.lck.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigContext {
    @Bean
    Logger.Level defFeiLog(){
        return Logger.Level.FULL;
    }
}
