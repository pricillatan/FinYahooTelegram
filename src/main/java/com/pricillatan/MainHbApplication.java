package com.pricillatan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication(scanBasePackages = "com.pricillatan")
@EnableScheduling
public class MainHbApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainHbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainHbApplication.class, args);
        
    }


    @Bean(name = "threadPoolExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(2000);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("AsyncTask-");
        executor.initialize();
        return executor;
    }


    @Bean
    public ScheduledExecutorService getScheduledExecutorService() {
        LOGGER.info("init schedule service..");
        return Executors.newSingleThreadScheduledExecutor();
    }


}
