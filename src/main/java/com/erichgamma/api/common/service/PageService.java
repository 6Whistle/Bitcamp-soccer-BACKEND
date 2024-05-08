package com.erichgamma.api.common.service;

import com.erichgamma.api.common.model.PageDto;
import org.springframework.data.domain.Pageable;

public interface PageService {
    PageDto getPageDto(Pageable page);
}
