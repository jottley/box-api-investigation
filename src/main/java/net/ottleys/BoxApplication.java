package net.ottleys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({BoxApplicationConfiguration.class})
public class BoxApplication {


    public static void main(String[] args) {
        SpringApplication.run(BoxApplication.class, args);
    }
}
