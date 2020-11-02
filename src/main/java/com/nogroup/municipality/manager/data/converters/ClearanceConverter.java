package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nogroup.municipality.manager.data.embedded.Clearance;

@SuppressWarnings("rawtypes")
public class ClearanceConverter implements AttributeConverter<Clearance, String>{


    @Override
    public String convertToDatabaseColumn(Clearance obj) {
        ObjectMapper om = new ObjectMapper();
        String op = null;
        try {
            op = om.writeValueAsString(obj);
            return (op);
        } catch (JsonProcessingException _x) {
        }
        return op ;
    }

    @Override
    public Clearance convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        Clearance op = null;
        try {
            op = om.readValue(val, Clearance.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }

}