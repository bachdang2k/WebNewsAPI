package com.rest.api.utils;

import com.rest.api.entity.Post;
import org.springframework.data.domain.Page;

public class PageUtil {

    public static PageDTO<Post> calculatePage(int size, int page, long totalElement) {
        PageDTO<Post> pageDTO = new PageDTO<>();
        boolean isFirst = false;
        boolean isLast = false;
        long totalPage = 0;

        totalPage = totalElement % size == 0 ? (totalElement/size) : (totalElement/size + 1);

        if (page == totalPage) {
            isLast = true;
        }

        if (page == 1) {
            isFirst = true;
        }

        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setFirst(isFirst);
        pageDTO.setLast(isLast);
        pageDTO.setTotalPage(totalPage);
        pageDTO.setTotalElement(totalElement);

        return pageDTO;
    }
}
