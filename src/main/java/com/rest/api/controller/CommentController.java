package com.rest.api.controller;

import com.rest.api.entity.Comment;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.service.CommentService;
import com.rest.api.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getAllComment() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.save(commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CommentResponseDTO>> findCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentResponseDTO> updateCommentById(@RequestParam("id") Long id, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.update(commentDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }
}
