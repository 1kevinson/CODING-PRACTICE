package com.wealcome.wealhome.integration;

import com.wealcome.wealhome.TestContainers;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "com.wealcome.wealhome")
@EntityScan("com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities")
@EnableJpaRepositories("com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa")
public class BaseIntegration extends TestContainers {

}