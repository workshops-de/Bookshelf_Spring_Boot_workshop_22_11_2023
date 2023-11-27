package de.workshops.bookshelf.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SwaggerConfigurationTest {

    @Nested
    class WithDefaultProfile {

        private RestClient restClient;

        @BeforeEach
        void setUp(@LocalServerPort int port) {
            restClient = RestClient.create("http://localhost:" + port);
        }

        @Test
        void swagger_is_available() {
            ResponseEntity<String> response = restClient.get().uri("/swagger-ui/index.html").retrieve().toEntity(String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

    @Nested
    @ActiveProfiles("prod")
    class WithProdProfile {

        private RestClient restClient;

        @BeforeEach
        void setUp(@LocalServerPort int port) {
            restClient = RestClient.create("http://localhost:" + port);
        }

        @Test
        void swagger_is_not_available() {
            try {
                restClient.get().uri("/swagger-ui/index.html").retrieve().toEntity(String.class);
                fail("404 expected");
            } catch (NotFound expected) {
            }
        }
    }
}
