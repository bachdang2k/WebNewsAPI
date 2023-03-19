package com.rest.api.utils;

import com.rest.api.utils.request.PostDTO;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {

    public static Map<String, String> validatePostDTO(PostDTO postDTO) {

        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                .fieldName("title")
                .value(postDTO.getTitle())
                .required(true)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("description")
                .value(postDTO.getDescription())
                .required(false)
                .maxLength(50)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("maximumOfComments")
                .value(postDTO.getMaximumOfComments())
                .required(true)
                .min(1L)
                .max(10L)
                .isInteger(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("content")
                .value(postDTO.getContent())
                .required(true)
                .maxLength(100)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("phoneNumber")
                .value(postDTO.getPhoneNumber())
                .required(true)
                .maxLength(11)
                .onlyNumber(true)
                .build().validate());

        return errors;
    }
}
