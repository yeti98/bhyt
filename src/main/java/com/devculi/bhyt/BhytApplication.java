package com.devculi.bhyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BhytApplication {

    public static void main(String[] args) {
        SpringApplication.run(BhytApplication.class, args);
    }

}
