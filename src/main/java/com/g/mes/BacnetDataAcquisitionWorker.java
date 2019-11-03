package com.g.mes;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BacnetDataAcquisitionWorker extends Thread {
    private EntityManagerFactory sessionFactory;

    private BacnetDeviceConfiguration configuration;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private AtomicBoolean running = new AtomicBoolean();

    public BacnetDataAcquisitionWorker(EntityManagerFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
//        log.info("启动数据采集任务定时器!!!");
//        CleanTask cleanTask = new CleanTask(sessionFactory, maxHistory);
//        executor.scheduleAtFixedRate(cleanTask, 10, 60, TimeUnit.SECONDS);
    }

    public void shutdown() {
//        log.info("关闭数据采集任务定时器!!!");
//        ExecutorUtil.shutdownAndAwaitTermination(executor, 60, TimeUnit.SECONDS);
    }
}
