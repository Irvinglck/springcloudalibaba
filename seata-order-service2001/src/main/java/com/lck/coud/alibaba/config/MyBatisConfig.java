package com.lck.coud.alibaba.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lck.coud.alibaba.dao"})
public class MyBatisConfig {
}
