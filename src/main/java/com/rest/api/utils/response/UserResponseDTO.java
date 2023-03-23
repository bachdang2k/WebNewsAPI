package com.rest.api.utils.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Date startTime;
    private Date endTime;
    private Date createdDate;
    private Date lastModifiedDate;

}
