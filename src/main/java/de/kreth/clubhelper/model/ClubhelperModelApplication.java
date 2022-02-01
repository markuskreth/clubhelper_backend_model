package de.kreth.clubhelper.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
//@EnableWebMvc
public class ClubhelperModelApplication {

    public static void main(String[] args) {
	SpringApplication.run(ClubhelperModelApplication.class, args);
    }

}
