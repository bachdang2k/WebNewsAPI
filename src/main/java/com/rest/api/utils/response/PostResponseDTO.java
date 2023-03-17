package com.rest.api.utils.response;

import lombok.Data;

import java.util.Set;

@Data
public class PostResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentResponseDTO> comments;
}
