package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.codehaus.jackson.map.ObjectMapper;

import com.nogroup.municipality.manager.data.embedded.VPolygon;

public class VPolygonConverter implements AttributeConverter<VPolygon, String>{


    @Override
    public String convertToDatabaseColumn(VPolygon obj) {
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
    public VPolygon convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        VPolygon op = null;
        try {
            op = om.readValue(val, VPolygon.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }
    
}