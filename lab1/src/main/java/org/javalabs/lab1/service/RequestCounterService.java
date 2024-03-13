package org.javalabs.lab1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestCounterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCounterService.class);
    private static AtomicInteger requestCount = new AtomicInteger(0);

    public static synchronized void incrementRequestCount() {
        requestCount.incrementAndGet();
    }

    public static synchronized int getRequestCount() {
        return requestCount.get();
    }

    public static void printRequestCount() {
        LOGGER.info("My Message");
        LOGGER.info("Current request count: " + getRequestCount());
    }
}
