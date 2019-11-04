package com.g.mes;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BacnetDeviceConfigurationTest {
    private static final String ConfigFile = "/bacnet-device-config.json";

    @Test
    public void fromResource() throws IOException, ClassNotFoundException {
        BacnetDeviceConfiguration cfg = BacnetDeviceConfiguration.fromResource(ConfigFile);
        assertNotNull(cfg);
    }
}