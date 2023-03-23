package com.rest.api.mapper;

import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.entity.User;
import com.rest.api.service.UserService;
import com.rest.api.utils.request.UserDTO;
import com.rest.api.utils.response.CommentResponseDTO;
import com.rest.api.utils.response.PostResponseDTO;
import com.rest.api.utils.response.UserResponseDTO;

import java.util.Date;
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

    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setStartTime(user.getStartTime());
        userResponseDTO.setEndTime(user.getEndTime());
        userResponseDTO.setCreatedDate(user.getCreatedDate());
        userResponseDTO.setLastModifiedDate(new Date());

        return userResponseDTO;
    }

    public static User toUse(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setStartTime(userDTO.getStartTime());
        user.setEndTime(userDTO.getEndTime());
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());

        return user;
    }


}
