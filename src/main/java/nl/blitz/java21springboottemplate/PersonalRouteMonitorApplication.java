package nl.blitz.java21springboottemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonalRouteMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalRouteMonitorApplication.class, args);
    }

}
