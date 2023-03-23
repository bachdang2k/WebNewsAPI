package com.rest.api.repository.impl;

import com.rest.api.entity.Post;
import com.rest.api.repository.PostRepositoryCustom;
import com.rest.api.utils.PageDTO;
import com.rest.api.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final EntityManager entityManager;
    @Override
    public PageDTO<Post> findAllWithCustomPage(int size, int page, String directory, String properties, String content, String title) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

//        CriteriaQuery<Long> count = criteriaBuilder.createQuery(Long.class);
//        count.select(criteriaBuilder.count(count.from(Post.class)));
//
//        //get total element to calculate page
//        Long totalElements = entityManager.createQuery(count).getSingleResult();

        //write query to get data
        CriteriaQuery<Post> getPostQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> from = getPostQuery.from(Post.class);

        CriteriaQuery<Post> select = getPostQuery.select(from);

        List<Predicate> predicates = new ArrayList<>();


        //solve search like operator
        if (!ObjectUtils.isEmpty(content)) {
            predicates.add(criteriaBuilder.like(from.get("content"), "%" + content + "%"));
        }

        if (!ObjectUtils.isEmpty(title)) {
            predicates.add(criteriaBuilder.like(from.get("title"), "%" + title + "%"));
        }

        select.select(from).where(predicates.toArray(new Predicate[]{}));

        //solve directory and sort
        if (directory.equalsIgnoreCase("desc") && !ObjectUtils.isEmpty(properties)) {
            getPostQuery.orderBy(criteriaBuilder.desc(from.get(properties)));
        } else if (directory.equalsIgnoreCase("asc") && !ObjectUtils.isEmpty(properties)){
            getPostQuery.orderBy(criteriaBuilder.asc(from.get(properties)));
        }

        //solve page and size
        TypedQuery<Post> typedQuery = entityManager.createQuery(select);

        //count total elements
        long totalCount = typedQuery.getResultList().size();

        int offSet = (page - 1) * size;
        typedQuery.setFirstResult(offSet);
        typedQuery.setMaxResults(size);

        //set data and return
        PageDTO<Post> pageDTO = PageUtil.calculatePage(size, page, totalCount);
        pageDTO.setData(typedQuery.getResultList());

        return pageDTO;
    }
}
