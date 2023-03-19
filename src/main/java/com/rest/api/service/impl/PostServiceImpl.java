package com.rest.api.service.impl;

import com.rest.api.entity.Post;
import com.rest.api.error.ResourceNotFoundException;
import com.rest.api.mapper.Mapper;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(Mapper::toPostResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id = " + id));
        return Optional.of(Mapper.toPostResponseDTO(post));
    }

    @Override
    public PostResponseDTO save(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setMaximumOfComments(postDTO.getMaximumOfComments());

        Post savedPost = postRepository.save(post);
        return Mapper.toPostResponseDTO(savedPost);
    }

    @Override
    public PostResponseDTO update(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id = " + id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return Mapper.toPostResponseDTO(post);
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found to delete with id = " + id));
        postRepository.deleteById(id);
        return "Delete Successfully";
    }
}
