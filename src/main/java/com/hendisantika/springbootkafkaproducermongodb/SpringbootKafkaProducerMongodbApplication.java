package com.hendisantika.springbootkafkaproducermongodb;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootKafkaProducerMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaProducerMongodbApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring REST Sample with Swagger")
                        .description("Simple Inventory Distributed Application using kafka")
                        .version("2.0")
                        .contact(new Contact()
                                .name("hendisantika")
                                .email("hendisantika@yahoo.co.id"))
                        .license(new License()
                                .name("APL V2")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
