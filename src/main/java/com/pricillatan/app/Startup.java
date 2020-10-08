package com.pricillatan.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Startup implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Startup.class);

    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    @Value("${schedule.job.refresh.cache.frequency}")
    private int jobFrequency = 5 * 60;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Start up..");
    }
}
