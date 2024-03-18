package com.zmz.chatgpt.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatgptDataApplication {

    public static void main(String[] args){
        SpringApplication.run(ChatgptDataApplication.class);
    }

}
