package com.shopmeowmeow.converter;

import com.shopmeowmeow.validators.ValidateDate;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@FacesConverter("convertDate")
public class ConvertDate implements Converter {

    private static final String[] OUTPUT_FORMATS = {
            "yyyy-MM-dd",
            "yyyyMMdd",
            "yyyy/MM/dd"
    };

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValidateDate validator = new ValidateDate();
        LocalDate date = validator.parseDate(value);
        if (date != null) {
            return date;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof LocalDate) {
            LocalDate date = (LocalDate) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_FORMATS[0]);
            return date.format(formatter);
        }
        return null;
    }
}
