package com.pricillatan.common.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pricillatan.app.jobs.MySummaryJob;


@Component
@Configuration
public class ScheduledTasks {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	MySummaryJob mySummaryJob;

//	@Scheduled(fixedRateString = "${app.scheduler.intervalms}")

	@Scheduled(cron = "${app.scheduler.cron}")
	public void monitorDB() {
		LOGGER.info("Checking.... {}", dateFormat.format(new Date()));
		mySummaryJob.getMyList();
	}
}
