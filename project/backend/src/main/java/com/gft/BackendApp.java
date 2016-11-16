package com.gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by kamu on 8/9/2016.
 */
@SpringBootApplication(scanBasePackages = "com.gft")
public class BackendApp {

    public static void main(String[] args) {
        SpringApplication.run(BackendApp.class, args);
    }

}
