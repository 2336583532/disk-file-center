package com.hnit.disk.config;

import feign.Contract;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @Author: liguangming
 * @Date: 2020/8/14
 */
public class FeignConfig {
    //配置是在FeignClient中的接口中使用Feign自带的注解
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }

    //禁用Hystrix
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
