package com.g.mes;

import lombok.Data;

@Data
public class BacnetDeviceProperty {
    private String name;
    private String desc;

    private String objectType;
    private int objectId;
}
