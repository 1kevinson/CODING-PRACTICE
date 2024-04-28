package com.wealcome.wealhome.e2e;

import com.wealcome.wealhome.TestContainers;
import com.wealcome.wealhome.WealHomeAppLauncher;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("prod")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = WealHomeAppLauncher.class
)
@TestPropertySource(locations = "classpath:application-prod.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class BaseE2E extends TestContainers {

}
