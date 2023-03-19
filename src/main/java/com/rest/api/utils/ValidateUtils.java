package com.rest.api.utils;

import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class ValidateUtils {

    private Object value;
    private boolean required;
    private Integer maxLength;
    private String fieldName;
    private String regex;
    private boolean onlyNumber;
    private boolean isInteger;
    private Long max;
    private Long min;

    public Map<String, String> validate() {

        Map<String, String> errors = new HashMap<>();

        //check field is required
        if (required && ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            errors.put(fieldName, fieldName + " is required");
        }

        //check max length of field
        if (!ObjectUtils.isEmpty(maxLength)
            && !ObjectUtils.isEmpty(value)
                && value.toString().length() > maxLength
                && !ObjectUtils.isEmpty(fieldName)
        ) {
            errors.put(fieldName, fieldName + " must have 0 - " + maxLength + " character");
        }

        //check field name is only number
        if (onlyNumber && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            String ONLY_NUMBER = "[0-9]+";
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher = patternOnlyNumber.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must contain only number");
            }
        }

        //check field name is Integer
        if (isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            try {
                Integer.parseInt(value.toString());
            } catch (Exception exception) {
                errors.put(fieldName, fieldName + " must is Integer number");
            }
        }

        if (!ObjectUtils.isEmpty(max)
                && ObjectUtils.isEmpty(min)
                && !ObjectUtils.isEmpty(value)
                && !ObjectUtils.isEmpty(fieldName))
        {
            try {
                double valueDouble = Double.parseDouble(value.toString());
                if (valueDouble < min || valueDouble > max) {
                    errors.put(fieldName, fieldName + " must range from " + min + " to " + max);
                }
            } catch (Exception exception) {
                errors.put(fieldName, fieldName + " is invalid data type");
            }
        }

        return errors;
    }

}
