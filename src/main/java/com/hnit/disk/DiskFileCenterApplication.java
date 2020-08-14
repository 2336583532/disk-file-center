package com.hnit.disk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DiskFileCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiskFileCenterApplication.class, args);
    }

}
