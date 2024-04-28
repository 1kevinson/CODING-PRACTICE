package com.wealcome.wealhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.wealcome.wealhome")
public class WealHomeAppLauncher {

    public static void main(String[] args) {
        SpringApplication.run(WealHomeAppLauncher.class, args);
    }

}
