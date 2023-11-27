package de.workshops.bookshelf.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
class ServerPortInProdTest {

    @Value("${server.port}")
    int port;

    @Test
    void port_is_8090() {
        assertThat(port).isEqualTo(8090);
    }
}
