package org.nttdata.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false",
        "jwt.secret=TestSecretKeyMustBe256BitsLongForHS256AlgorithmOkayNowThisIsLong",
        "jwt.expiration=86400000"
})
class SpringReservasNttDataApplicationTests {

    @Test
    void contextLoads() {
    }

}
