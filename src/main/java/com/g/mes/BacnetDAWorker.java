package com.g.mes;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkBuilder;
import com.serotonin.bacnet4j.transport.DefaultTransport;
import com.serotonin.bacnet4j.type.constructed.ObjectPropertyReference;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.util.PropertyReferences;
import com.serotonin.bacnet4j.util.PropertyValues;
import com.serotonin.bacnet4j.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BacnetDAWorker extends Thread {
    private static final Logger log = LoggerFactory.getLogger(BacnetDAWorker.class);

    private static final String ConfigFile = "/bacnet-device-config.json";

    private EntityManagerFactory sessionFactory;

    private Integer interval; // 数据上报间隔，单位：秒

    private BacnetDeviceConfiguration cfg;

    private LocalDevice localDevice = null;
    private RemoteDevice remoteDevice = null;

    private ScheduledExecutorService executor;

    private AtomicBoolean running = new AtomicBoolean();

    public BacnetDAWorker(EntityManagerFactory sessionFactory, Integer interval) {
        super("DAWorker");

        this.sessionFactory = sessionFactory;
        this.interval = interval;

        // 读系统配置
        config();
    }

    private void config() {
        try {
            log.info("读系统配置{}...", ConfigFile);
            cfg = BacnetDeviceConfiguration.fromResource(ConfigFile);
        } catch (Exception e) {
            log.error("从资源文件恢复系统配置失败，请检查资源文件是否在指定路径，并且格式正确", e);
        }
    }

    private void init() {
        initLocalDevice();
        initRemoteDevice();
        executor = Executors.newScheduledThreadPool(1);
    }

    /**
     * 获取远程设备的扩展属性
     */
    private void getExtendedDeviceInformation() throws BACnetException {
        log.info("获取远程设备的扩展属性");
        PropertyReferences refs = new PropertyReferences();
        refs.add(remoteDevice.getObjectIdentifier(),
                PropertyIdentifier.protocolObjectTypesSupported,
                PropertyIdentifier.protocolServicesSupported,
                PropertyIdentifier.protocolVersion,
                PropertyIdentifier.objectName,
                PropertyIdentifier.segmentationSupported,
                PropertyIdentifier.maxApduLengthAccepted,
                PropertyIdentifier.apduTimeout,
                PropertyIdentifier.vendorIdentifier,
                PropertyIdentifier.vendorName,
                PropertyIdentifier.modelName);
        PropertyValues pvs = RequestUtils.readProperties(localDevice, remoteDevice, refs, true, null);
        for (ObjectPropertyReference opr : pvs) {
            if (remoteDevice.getObjectIdentifier().equals(opr.getObjectIdentifier())) {
                remoteDevice.setDeviceProperty(opr.getPropertyIdentifier(), pvs.getNoErrorCheck(opr));
                log.info(String.format("\t%s = %s", opr.getPropertyIdentifier().toString(), pvs.getNoErrorCheck(opr)));
            }
        }
    }

    private void initLocalDevice() {
        IpNetworkBuilder builder;
        IpNetwork network;
        int deviceNumber = ObjectIdentifier.UNINITIALIZED;

        while (localDevice == null) {
            try {
                log.info("初始化localDevice...");
                if (cfg.getLocalDeviceNumber() != null) {
                    deviceNumber = cfg.getLocalDeviceNumber();
                }
                builder = new IpNetworkBuilder();
                if (cfg.getLocalAddress() != null && cfg.getLocalAddress().trim().length() > 0) {
                    builder.withLocalBindAddress(cfg.getLocalAddress());
                }
                if (cfg.getLocalPort() != null && cfg.getLocalPort() != 0) {
                    builder.withPort(cfg.getLocalPort());
                }
                builder.withBroadcast(cfg.getBroadcastAddress(), cfg.getNetworkPrefix());
                network = builder.build();
                localDevice = new LocalDevice(deviceNumber, new DefaultTransport(network));
                localDevice.initialize();
                log.info("初始化localDevice成功：{}", localDevice);
            } catch (Exception e) {
                log.error("初始化localDevice失败", e);
                log.error("1分钟后重试");
                if (localDevice != null) {
                    localDevice.terminate();
                    localDevice = null;
                }
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private void initRemoteDevice() {
        while (remoteDevice == null) {
            try {
                log.info("连接remoteDevice...");
                remoteDevice = localDevice.getRemoteDeviceBlocking(cfg.getRemoteDeviceNumber(), cfg.getRemoteDeviceTimeoutMillis());
                getExtendedDeviceInformation();
                log.info("连接remoteDevice成功");
            } catch (BACnetException e) {
                log.error("连接remoteDevice失败", e);
                log.error("清空localDevice缓存，1分钟后重试");
                localDevice.clearRemoteDevices();
                remoteDevice = null;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    @Override
    public void run() {
        log.info("启动数据采集任务定时器!!!");

        running.set(true);

        while (running.get()) {

            // 初始化
            init();

            BacnetDATask dat = new BacnetDATask(sessionFactory, localDevice, remoteDevice, cfg);
            final ScheduledFuture<?> daf = executor.scheduleWithFixedDelay(dat, interval, interval, TimeUnit.SECONDS);

            while (running.get() && !daf.isDone() && !daf.isCancelled()) {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ignore) {
                }
            }

            shutdownInternal();
        }
    }

    private void shutdownInternal() {
        log.info("关闭相关设备!!!");
        if (localDevice != null) {
            localDevice.terminate();
            localDevice = null;
        }
        if (remoteDevice != null) {
            remoteDevice = null;
        }
        if (executor != null) {
            ExecutorUtil.shutdownAndAwaitTermination(executor, 60, TimeUnit.SECONDS);
            executor = null;
        }
    }

    public void shutdown() {
        log.info("关闭数据采集任务定时器!!!");
        running.set(false);
        shutdownInternal();
    }
}
