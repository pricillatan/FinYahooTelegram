package com.pricillatan.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

@Component
public class Shutdown implements ApplicationListener<ContextClosedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Shutdown.class);

    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        LOGGER.info("Shutdown..");

        scheduledExecutorService.shutdownNow();

    }
}
