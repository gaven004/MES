package com.g.mes;

import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class BacnetDeviceConfiguration {
    /**
     * 物理设备参数
     */
    private Integer localDeviceNumber;
    private String localAddress; // nullable, default value: "0.0.0.0"
    private Integer localPort; // nullable, default value: 0xBAC0 = 47808
    private String broadcastAddress; // the broadcast address for the network
    private Integer networkPrefix; // the number of bits in the local subnet.
    private Integer remoteDeviceNumber;
    private Integer remoteDeviceTimeoutMillis = 10000;

    private List<BacnetDevice> devices;

    /**
     * 从资源文件恢复系统配置
     *
     * @param name 资源文件名
     * @return 系统配置
     */
    public static BacnetDeviceConfiguration fromResource(String name) throws IOException, ClassNotFoundException {
        BacnetDeviceConfiguration configuration = ConfigurationHelper.fromResource(name, BacnetDeviceConfiguration.class);
        configuration.after();
        return configuration;
    }

    /**
     * 检验
     */
    void validate() {
        // todo
    }

    /**
     * 整理数据，构建相关map
     */
    void after() throws ClassNotFoundException {
        if (devices != null && !devices.isEmpty()) {
            for (BacnetDevice device : devices) {
                if (device.getProperties() != null && !device.getProperties().isEmpty()) {
                    List<ObjectIdentifier> oids = new CopyOnWriteArrayList<>();
                    Map<ObjectIdentifier, BacnetDeviceProperty> propertyMap = new ConcurrentHashMap<>();
                    for (BacnetDeviceProperty property : device.getProperties()) {
                        ObjectIdentifier oid =
                                new ObjectIdentifier(ObjectType.forName(property.getObjectType()), property.getObjectId());
                        oids.add(oid);
                        propertyMap.put(oid, property);
                    }
                    device.setOids(oids);
                    device.setPropertyMap(propertyMap);
                }
            }
        }
    }
}
