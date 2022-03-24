package com.kangaroo.interview.v1.configs;

import org.springframework.context.annotation.Bean;
import java.time.Clock;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig
{
    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}