package com.g.mes;

import lombok.Data;

@Data
public class Property {
    private String name;
    private Object value;
    private Class type;
    private int index;
}
