package com.nas.gitpipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitPipelineApplication {

    public String hello(){
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(GitPipelineApplication.class, args);
    }

}
