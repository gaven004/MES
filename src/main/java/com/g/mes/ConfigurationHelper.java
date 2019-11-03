package com.g.mes;

import com.alibaba.fastjson.JSON;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class ConfigurationHelper {
    /**
     * 从资源文件恢复系统配置
     *
     * @param name 资源文件名
     * @return 系统配置
     */
    public static <T> T fromResource(String name, Type clazz) throws IOException, ClassNotFoundException {
        InputStream in = null;

        try {
            in = Configuration.class.getResourceAsStream(name);
        } catch (Exception ignore) {
        }

        if (in == null) {
            if (name.startsWith("/")) {
                final String stripped = name.substring(1);
                in = ClassLoader.getSystemResourceAsStream(stripped);
            }
        }

        if (in == null) {
            throw new FileNotFoundException();
        }

        try {
            return JSON.parseObject(in, StandardCharsets.UTF_8, clazz);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}