package com.g.mes;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Entity {
    @NonNull
    private String name;
    private List<Property> properties = new ArrayList<>();

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void addProperty(String name, Object value) {
        Property property = new Property();
        property.setName(name);
        property.setValue(value);
        addProperty(property);
    }
}
