package com.example.webapplication.config;

import com.example.webapplication.Email.UserActivityScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class JobConfig {

    @Bean
    public UserActivityScheduler reminderInactiveUser() {
        return new UserActivityScheduler();
    }

}
