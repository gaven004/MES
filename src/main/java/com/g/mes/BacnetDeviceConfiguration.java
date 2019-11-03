package com.g.mes;

import lombok.Data;

import java.io.IOException;

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
    }
}
