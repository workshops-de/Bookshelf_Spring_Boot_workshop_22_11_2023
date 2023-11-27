package de.workshops.bookshelf.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class SwaggerConfiguration {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Bookshelf API")
                                .version("v0.0.1")
                                .license(new License()
                                        .name("MIT License")
                                        .url("https://opensource.org/licenses/MIT")
                                )
                );
    }
}
