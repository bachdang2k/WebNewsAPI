package com.rest.api.service.impl;

import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.error.ResourceNotFoundException;
import com.rest.api.mapper.Mapper;
import com.rest.api.repository.PostRepository;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.repository.CommentRepository;
import com.rest.api.service.CommentService;
import com.rest.api.utils.response.CommentResponseDTO;
import com.rest.api.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(Mapper::toCommentResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id = " + id));
        return Optional.of(Mapper.toCommentResponseDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id = " + commentDTO.getPostId()));
        Comment comment = new Comment();
        comment.setName(commentDTO .getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        return Mapper.toCommentResponseDTO(savedComment);
    }

    @Override
    public CommentResponseDTO update(CommentDTO commentDTO, Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id = " + id));

        Post post = postRepository.findById(commentDTO.getPostId())
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id = " + commentDTO.getPostId()));
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        comment.setPost(post);
        return Mapper.toCommentResponseDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id = " + id));
        commentRepository.deleteById(id);
        return "Delete Successfully";
    }
}
