package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String phoneNumber;
    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ha_Noi")
    private Date startTime;
    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ha_Noi")
    private Date endTime;
    @JsonProperty("created_date")
    @JsonFormat(pattern = "HH-mm", timezone = "Asia/Ha_Noi")
    private Date createdDate;
    @JsonProperty("last_Modified_date")
    @JsonFormat(pattern = "HH-mm-ss", timezone = "Asia/Ha_Noi")
    private Date lastModifiedDate;

}
