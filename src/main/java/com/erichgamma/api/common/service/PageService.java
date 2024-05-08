package com.erichgamma.api.common.service;

import com.erichgamma.api.common.model.PageDTO;

public interface PageService {
     PageDTO getPageDTO(Long pageSize, Long totalCount ,Long pageNumber);
}
