package com.velox.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NotificationServiceApplication {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new CustomTheradFactory("SiemThread");
        RejectedExecutionHandler customHasndler = new CustomRejectedExecutionHandler();
        SpringApplication.run(NotificationServiceApplication.class, args);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));

        Future<?> task1 = executor.submit(() -> {
            System.out.println("hello");
            return 10;
        });
        System.out.println(task1.isDone());




    }


    static class CustomTheradFactory implements ThreadFactory {
        String poolName;
        public CustomTheradFactory(String poolName) {
            this.poolName = poolName;
        }
        AtomicInteger threadCount = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            String threadName = "Thread  :" + poolName + threadCount.getAndIncrement();
            Thread thread = new Thread(r, threadName);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

    static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("ALERT: Task " + r.toString() + " was rejected! Current Active Threads: " + executor.getActiveCount() + ", Remaining Queue Capacity: " + executor.getQueue().remainingCapacity());

        }

    }
}
