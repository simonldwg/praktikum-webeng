package com.shopmeowmeow.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FacesValidator("validateDate")
public class ValidateDate implements Validator {

    private static final String[] ACCEPTED_FORMATS = {
            "yyyy-MM-dd",
            "yyyyMMdd",
            "yyyy/MM/dd"
    };

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        LocalDate date = parseDate(value.toString());
        if (date == null) {
            throw new ValidatorException(new FacesMessage("Invalid date format"));
        }

        // Überprüfen des Jahres
        int year = date.getYear();
        if (year < 1900) {
            throw new ValidatorException(new FacesMessage("Year cannot be before 1900"));
        }

        // Überprüfen, ob das Datum in der Zukunft liegt
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate)) {
            throw new ValidatorException(new FacesMessage("Date cannot be in the future"));
        }
    }

    public LocalDate parseDate(String dateString) {
        for (String format : ACCEPTED_FORMATS) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

            try {
                return LocalDate.parse(dateString, formatter);
            } catch (Exception e) {
                // Ignore and try the next format
            }
        }

        return null;
    }
}
