package com.lck.cloud.alibaba.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lck.cloud.alibaba.dao"})
public class MyBatisConfig {
}
