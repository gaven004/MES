package com.g.mes;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.ObjectPropertyReference;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.util.PropertyValues;
import com.serotonin.bacnet4j.util.RequestUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import java.beans.PropertyDescriptor;

public class BacnetDATask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(BacnetDATask.class);

    private EntityManagerFactory sessionFactory;

    private LocalDevice ld;
    private RemoteDevice rd;

    private BacnetDeviceConfiguration cfg;

    public BacnetDATask(EntityManagerFactory sessionFactory, LocalDevice ld, RemoteDevice rd, BacnetDeviceConfiguration cfg) {
        this.sessionFactory = sessionFactory;
        this.ld = ld;
        this.rd = rd;
        this.cfg = cfg;
    }

    @Override
    public void run() {
        log.info("启动数据采集上报任务...");

        boolean error = false;

        for (BacnetDevice device : cfg.getDevices()) {
            log.info("读取设备[id: {}, type: {}]数值：", device.getId(), device.getType());

            Object entity = createEntiry(device.getType());
            if (entity == null) {
                log.warn("未知的实体类型：{}，忽略", device.getType());
                continue;
            }

            try {
                PropertyUtils.setProperty(entity, "deviceId", device.getId());
                PropertyValues pvs = RequestUtils.readOidPresentValues(ld, rd, device.getOids(), null);
                for (ObjectPropertyReference opr : pvs) {
                    Encodable value = pvs.get(opr);
                    log.info("\t{} = {}", opr.getObjectIdentifier().toString(), value);

                    BacnetDeviceProperty deviceProperty = device.getProperty(opr.getObjectIdentifier());

                    PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(entity, deviceProperty.getName());
                    try {
                        Object target = null;

                        if (Float.class.equals(propertyDescriptor.getPropertyType())) {
                            target = ((Real) value).floatValue();
                        } else if (Integer.class.equals(propertyDescriptor.getPropertyType())) {
                            target = (int) ((Real) value).floatValue();
                        } else if (Double.class.equals(propertyDescriptor.getPropertyType())) {
                            target = (double) ((Real) value).floatValue();
                        } else {
                            target = value.toString();
                        }

                        PropertyUtils.setProperty(entity, deviceProperty.getName(), target);
                    } catch (Exception ignore) {
                    }
                }
                PersistenceUtil.persist(sessionFactory, entity, log);
            } catch (Exception e) {
                error = true;
                log.warn("采集任务异常", e);
            }
        }

        if (error) {
            // 抛出异常，通知主线程重新初始化
            throw new RuntimeException();
        }

        log.info("数据采集上报任务结束!");
    }

    private Object createEntiry(String type) {
        if ("flowmeter".equals(type)) {
            return new FlowmeterDatEntity();
        } else if ("powermeter".equals(type)) {
            return new PowermeterDatEntity();
        }

        return null;
    }
}
