package com.rest.api.mapper;

import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.utils.response.CommentResponseDTO;
import com.rest.api.utils.response.PostResponseDTO;

import java.util.stream.Collectors;

public class Mapper {

    public static CommentResponseDTO toCommentResponseDTO(Comment comment) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setName(comment.getName());
        commentResponseDTO.setEmail(comment.getEmail());
        commentResponseDTO.setBody(comment.getBody());
        commentResponseDTO.setPost(comment.getPost());
        return commentResponseDTO;
    }

    public static PostResponseDTO toPostResponseDTO(Post post) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(post.getId());
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setDescription(post.getDescription());
        postResponseDTO.setContent(post.getContent());
        postResponseDTO.setMaximumOfComments(post.getMaximumOfComments());
        if (post.getComments() != null && post.getComments().size() > 0){
            postResponseDTO.setComments(post.getComments().stream().map(Mapper::toCommentResponseDTO).collect(Collectors.toSet()));
        }
        return postResponseDTO;
    }

}
