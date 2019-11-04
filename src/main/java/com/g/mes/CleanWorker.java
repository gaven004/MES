package com.g.mes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CleanWorker extends Thread {
    private static final Logger log = LoggerFactory.getLogger(CleanWorker.class);

    private EntityManagerFactory sessionFactory;

    private Integer maxHistory; // 数据保存年限，单位：年，0表示全部保留

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public CleanWorker(EntityManagerFactory sessionFactory, Integer maxHistory) {
        super("CleanWorker");
        this.maxHistory = maxHistory;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        log.info("启动清理历史数据任务定时器!!!");
        CleanTask cleanTask = new CleanTask(sessionFactory, maxHistory);
        executor.scheduleAtFixedRate(cleanTask, 1, 1, TimeUnit.DAYS);
    }

    public void shutdown() {
        log.info("关闭清理历史数据任务定时器!!!");
        ExecutorUtil.shutdownAndAwaitTermination(executor, 60, TimeUnit.SECONDS);
    }
}
