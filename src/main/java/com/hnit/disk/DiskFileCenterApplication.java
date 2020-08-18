package com.hnit.disk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class DiskFileCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiskFileCenterApplication.class, args);
    }

}
