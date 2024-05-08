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

    public PageDto(Long pageSize, Long totalCount, Long pageNumber) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;

        pageCount = (totalCount % pageSize == 0) ? totalCount / pageSize : (totalCount / pageSize) + 1;
        blockCount = (totalCount % (BLOCK_SIZE * pageSize) == 0) ? totalCount / (BLOCK_SIZE * pageSize) : totalCount / (BLOCK_SIZE * pageSize) + 1;

        startRow = pageSize * (pageNumber - 1);
        endRow = startRow + (pageSize - 1);

        blockNumber = (pageNumber%BLOCK_SIZE==0) ? (pageNumber/BLOCK_SIZE) : (pageNumber/BLOCK_SIZE)+1;

        startPage = (BLOCK_SIZE * (blockNumber-1)) + 1;
        endPage = (startPage + BLOCK_SIZE) - 1;

        nextBlock = blockNumber + 1;
        prevBlock = blockNumber - 1;

        existPrev = prevBlock != 0 ? true : false;
        existNext = blockCount >= nextBlock ? true : false;

    }
}


