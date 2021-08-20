package com.mastery.java.task.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Simple Web Api")
                .version("1.0")
                .contact(new Contact()
                        .email("shurik.yankovich@gmail.com")
                        .name("Yankovich Aleksandr")));
    }
}
