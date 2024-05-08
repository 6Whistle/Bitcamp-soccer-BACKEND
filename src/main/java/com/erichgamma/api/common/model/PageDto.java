package com.erichgamma.api.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PageDto {
    private Long pageSize;
    private Long BLOCK_SIZE = 10L;
    private Long totalCount;
    private Long blockCount;
    private Long pageCount;
    private Long startRow;
    private Long endRow;
    private Long startPage;
    private Long endPage;
    private Long nextBlock;
    private Long prevBlock;
    private Long blockNumber;
    private Long pageNumber;
    private Boolean existPrev;
    private Boolean existNext;

}


