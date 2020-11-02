package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
public class HashMapConverter implements AttributeConverter<HashMap, String>{


    @Override
    public String convertToDatabaseColumn(HashMap obj) {
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
    public HashMap convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        HashMap op = null;
        try {
            op = om.readValue(val, HashMap.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }

}
