
package com.nogroup.municipality.manager.data.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeConverter;

public class ShortDateConverter implements AttributeConverter<Date, String>{


    @Override
    public Date convertToEntityAttribute(String obj) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dt = df.parse(obj);
        } catch (ParseException _x) {
        }
        return dt ;
    }

    @Override
    public String convertToDatabaseColumn(Date obj) {
        return new SimpleDateFormat("dd/MM/yyyy").format(obj) ;
    }

}
