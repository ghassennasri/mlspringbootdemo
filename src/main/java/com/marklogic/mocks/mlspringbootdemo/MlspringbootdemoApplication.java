package com.marklogic.mocks.mlspringbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MlspringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlspringbootdemoApplication.class, args);
    }

}
