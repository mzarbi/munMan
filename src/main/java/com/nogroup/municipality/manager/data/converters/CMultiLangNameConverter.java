package com.nogroup.municipality.manager.data.converters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nogroup.municipality.manager.data.embedded.CMultiLangName;

@SuppressWarnings("rawtypes")
public class CMultiLangNameConverter implements AttributeConverter<CMultiLangName, String>{


    @Override
    public String convertToDatabaseColumn(CMultiLangName obj) {
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
    public CMultiLangName convertToEntityAttribute(String val) {
        ObjectMapper om = new ObjectMapper();
        CMultiLangName op = null;
        try {
            op = om.readValue(val, CMultiLangName.class);
            return (op);
        } catch (IOException _x) {
        }
        return op ;
    }

}