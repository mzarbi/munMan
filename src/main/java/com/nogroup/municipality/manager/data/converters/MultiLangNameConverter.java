package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;

public class MultiLangNameConverter implements AttributeConverter<MultiLangName, String>{


    @Override
    public String convertToDatabaseColumn(MultiLangName obj) {
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
    public MultiLangName convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        MultiLangName op = null;
        try {
            op = om.readValue(val, MultiLangName.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }

}
