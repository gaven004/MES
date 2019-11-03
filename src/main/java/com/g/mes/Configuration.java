package com.g.mes;

import lombok.Data;

import java.io.IOException;

@Data
public class Configuration {
    /**
     * 系统参数
     */
    private Integer dataSubmitInterval; // 数据上报间隔，单位：秒
    private Integer maxHistory; // 数据保存年限，单位：年，0表示全部保留

    /**
     * 从资源文件恢复系统配置
     *
     * @param name 资源文件名
     * @return 系统配置
     */
    public static Configuration fromResource(String name) throws IOException, ClassNotFoundException {
        Configuration configuration = ConfigurationHelper.fromResource(name, Configuration.class);
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
