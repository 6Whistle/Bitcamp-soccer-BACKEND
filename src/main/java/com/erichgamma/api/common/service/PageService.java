package com.erichgamma.api.common.service;

import org.springframework.data.domain.Pageable;

import com.erichgamma.api.common.model.PageDto;

public interface PageService {
    public PageDto getPageDto(Long totalCount, Pageable page);
}
