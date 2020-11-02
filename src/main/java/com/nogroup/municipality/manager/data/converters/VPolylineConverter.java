package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.codehaus.jackson.map.ObjectMapper;

import com.nogroup.municipality.manager.data.embedded.VPolyline;

public class VPolylineConverter implements AttributeConverter<VPolyline, String>{


    @Override
    public String convertToDatabaseColumn(VPolyline obj) {
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
    public VPolyline convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        VPolyline op = null;
        try {
            op = om.readValue(val, VPolyline.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }
    
}