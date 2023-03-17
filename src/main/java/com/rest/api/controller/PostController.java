package com.rest.api.controller;

import com.rest.api.service.PostService;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.CommentResponseDTO;
import com.rest.api.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllComment() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> createComment(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.save(postDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PostResponseDTO>> findCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PostResponseDTO> updateCommentById(@RequestParam("idToUpdate") Long id, @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.update(postDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("idToDelete") Long id) {
        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
    }
}