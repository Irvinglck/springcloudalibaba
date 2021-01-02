package com.lck;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyDefRobbion {

    @Bean
    public RandomRule getRandomRule(){
        return new RandomRule();
    }
}
