package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.codehaus.jackson.map.ObjectMapper;
import org.vaadin.addon.vol3.feature.OLFeatureSerializer;

import com.nogroup.municipality.manager.data.embedded.VPoint;

public class TPointConverter extends OLFeatureSerializer implements AttributeConverter<VPoint, String>{


	@Override
    public String convertToDatabaseColumn(VPoint obj) {
        ObjectMapper om = new ObjectMapper();
        String op = null;
        try {
            op = om.writeValueAsString(obj);
            return (op);
        } catch (Exception _x) {
        }
        return op ;
    }

    @Override
    public VPoint convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        VPoint op = null;
        try {
            op = om.readValue(val, VPoint.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }
}