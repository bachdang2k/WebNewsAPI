package com.rest.api.repository;

import com.rest.api.entity.Post;
import com.rest.api.utils.PageDTO;

public interface PostRepositoryCustom {
    PageDTO<Post> findAllWithCustomPage(int size, int page, String directory, String properties, String content, String title);
}
