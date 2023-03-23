package com.rest.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private int page;
    private int size;
    private long totalPage;
    private long totalElement;
    private boolean isFirst;
    private boolean isLast;
    private List<T> data;

}
