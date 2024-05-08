package com.erichgamma.api.common.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erichgamma.api.common.model.PageDto;
import com.erichgamma.api.common.service.PageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    @Override
    public PageDto getPageDto(Long totalCount, Pageable pageable) {
        return new PageDto(pageable.getPageSize() + 0L, 10L, pageable.getPageNumber() + 0L, totalCount);
    }
    
}
