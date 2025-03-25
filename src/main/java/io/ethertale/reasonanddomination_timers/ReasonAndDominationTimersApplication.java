package io.ethertale.reasonanddomination_timers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReasonAndDominationTimersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReasonAndDominationTimersApplication.class, args);
    }

}
