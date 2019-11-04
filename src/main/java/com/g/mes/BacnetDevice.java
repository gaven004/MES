package com.g.mes;

import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BacnetDevice {
    private Integer id;
    private String type;
    private String desc;

    private List<BacnetDeviceProperty> properties;
    private List<ObjectIdentifier> oids;

    private Map<ObjectIdentifier, BacnetDeviceProperty> propertyMap;

    public BacnetDeviceProperty getProperty(ObjectIdentifier oid) {
        return propertyMap.get(oid);
    }
}
