package com.erichgamma.api.common.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private Long BLOCK_SIZE = 10L; //대문자 상수 처리.
    private Long pageSize ;
    private Long totalCount ;
    private Long pageNumber ;

    private Long blockCount ;
    private Long pageCount ;
    private Long startRow ;
    private Long endRow ;
    private Long blockNum ;
    private Long startPage ;
    private Long endPage ;
    private Long nextBlock ;
    private Long prevBlock ;

    private Boolean existPrev ;
    private Boolean existNext ;

    public PageDTO(Long pageSize, Long totalCount, Long pageNumber) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;

        pageCount =  ( (totalCount%pageSize) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1);
        blockCount =  ( (totalCount%(pageSize*BLOCK_SIZE)) == 0 ? totalCount/(pageSize*BLOCK_SIZE) : (totalCount/(pageSize*BLOCK_SIZE))+1);
        blockNum = pageNumber % BLOCK_SIZE == 0 ? pageNumber/ BLOCK_SIZE :  pageNumber/ BLOCK_SIZE +1;
        startRow =  pageSize*(pageNumber-1) ;
        endRow = pageNumber == pageCount ?  (totalCount - 1) :  startRow +pageSize-1;
        startPage = BLOCK_SIZE*(blockNum-1)+1;
        endPage = blockNum == blockCount ? pageCount -1 : startPage+BLOCK_SIZE-1;
        nextBlock = blockNum+1;
        prevBlock = blockNum -1;
        existPrev = prevBlock != 0;
        existNext = nextBlock != blockCount;


    }
}
