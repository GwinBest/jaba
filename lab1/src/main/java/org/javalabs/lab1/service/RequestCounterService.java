package org.javalabs.lab1.service;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestCounterService {

    private RequestCounterService() {}
    private static AtomicInteger requestCount = new AtomicInteger(0);

    public static synchronized void incrementRequestCount() {
        requestCount.incrementAndGet();
    }

    public static synchronized int getRequestCount() {
        return requestCount.get();
    }


}
