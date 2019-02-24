package com.leading.company.dataupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class DataUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataUploadApplication.class, args);
    }

}

