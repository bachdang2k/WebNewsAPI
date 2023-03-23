package com.rest.api.controller;

import com.rest.api.entity.Post;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.PageDTO;
import com.rest.api.utils.ValidateObject;
import com.rest.api.utils.ValidateUtils;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllComment() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createComment(@RequestBody PostDTO postDTO) {

        Map<String, String> errorValidator = ValidateObject.validatePostDTO(postDTO);
        if (!ObjectUtils.isEmpty(errorValidator)) {
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/getAllWithPage")
    public ResponseEntity<Page<Post>> getAllWithPage(Pageable pageable) {
        return new ResponseEntity<>(postRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllWithTitlePage")
    public ResponseEntity<Page<Post>> getAllWithTitlePage(@RequestParam(defaultValue = "", required = false) String title,
                                                          @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(postRepository.findByTitle(title, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllWithPageCustom")
    public ResponseEntity<PageDTO<Post>> getAllWithPageCustom (
                                            @RequestParam(defaultValue = "10", required = false) int size,
                                            @RequestParam(defaultValue = "1", required = false) int page,
                                            @RequestParam(defaultValue = "asc", required = false) String director,
                                            @RequestParam(defaultValue = "", required = false) String properties,
                                            @RequestParam(defaultValue = "", required = false) String content,
                                            @RequestParam(defaultValue = "", required = false) String title

    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postRepository.findAllWithCustomPage(size, page, director, properties, content, title)
        );
    }


}
