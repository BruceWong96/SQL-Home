package com.bruce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Bruce Wong
 * @date 2023年04月20日 23:29
 */
@SpringBootApplication
@MapperScan("com.bruce.mapper")
@EnableScheduling
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
