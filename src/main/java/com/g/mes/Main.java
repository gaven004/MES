package com.g.mes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String ConfigFile = "/config.json";

    private static Configuration cfg = null;

    private static EntityManagerFactory sessionFactory;

    private static CleanWorker cleanWorker;

    private static BacnetDAWorker dataAcquisitionWorker;

    public static void main(String[] args) {
        log.info("");
        log.info("");
        log.info("启动系统...");
        log.info("");

        // 注册退出钩子
        addShutdownHook();

        // 读系统配置
        config();

        // 初始化
        init();

        runTask();
    }

    private static void config() {
        try {
            log.info("读系统配置{}...", ConfigFile);
            cfg = Configuration.fromResource(ConfigFile);
        } catch (Exception e) {
            log.error("从资源文件恢复系统配置失败，请检查资源文件是否在指定路径，并且格式正确", e);
            log.error("退出系统！！！");
            System.exit(1);
        }
    }

    private static void init() {
        log.info("初始化...");
        sessionFactory = Persistence.createEntityManagerFactory("com.g.mes.jpa");
        cleanWorker = new CleanWorker(sessionFactory, cfg.getMaxHistory());
        dataAcquisitionWorker = new BacnetDAWorker(sessionFactory, cfg.getDataSubmitInterval());
    }

    private static void runTask() {
        cleanWorker.start();
        dataAcquisitionWorker.start();
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (cleanWorker != null) {
                cleanWorker.shutdown();
                dataAcquisitionWorker.interrupt();
                try {
                    cleanWorker.join();
                } catch (InterruptedException e) {
                }
            }
            if (dataAcquisitionWorker != null) {
                dataAcquisitionWorker.shutdown();
                dataAcquisitionWorker.interrupt();
                try {
                    dataAcquisitionWorker.join();
                } catch (InterruptedException e) {
                }
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
            log.info("退出系统！！！");
        }));
    }
}
