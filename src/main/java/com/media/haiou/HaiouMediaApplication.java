package com.media.haiou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HaiouMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaiouMediaApplication.class, args);
        System.out.println("启动成功:\nhttp://localhost:8080");
    }

}
