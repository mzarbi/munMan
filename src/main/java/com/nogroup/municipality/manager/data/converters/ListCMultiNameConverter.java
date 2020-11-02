package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
public class ListCMultiNameConverter implements AttributeConverter<List, String>{


    @Override
    public String convertToDatabaseColumn(List obj) {
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
    public List convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        List op = null;
        try {
            op = om.readValue(val, List.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }

}