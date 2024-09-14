package application_at_upstream_mobility.bored;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "application_at_upstream_mobility.bored")
public class BoredApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoredApplication.class, args);
    }
}

