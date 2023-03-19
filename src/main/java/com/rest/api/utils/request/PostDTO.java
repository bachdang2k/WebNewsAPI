package com.rest.api.utils.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class PostDTO {

    private String title;

    private String description;

    private String content;

    private Integer maximumOfComments;

    private String phoneNumber;
}
