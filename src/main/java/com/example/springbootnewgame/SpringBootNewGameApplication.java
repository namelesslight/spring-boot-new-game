package com.example.springbootnewgame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(value = "com.example.springbootnewgame.mapper")
@ServletComponentScan(value = "com.com.example.springbootnewgame.filter")
public class SpringBootNewGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNewGameApplication.class, args);
    }

}
