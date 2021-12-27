package com.jax.job.services.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronJob {

    @Scheduled(fixedDelay = 5000)
    public void printf() {
        System.out.println(LocalDateTime.now());
    }

    @Scheduled(cron = "0 0 * * *")
    public void demo() {
        System.out.println("jax tony hahahahahaha -------");
    }
}
