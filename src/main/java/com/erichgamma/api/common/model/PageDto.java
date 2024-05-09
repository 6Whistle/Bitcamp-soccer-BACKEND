package com.erichgamma.api.common.model;


import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDto {
        private Long pageSize;
        private Long blockSize;
        private Long pageNumber;
        private Long blockNumber;

        private Long totalCount;
        private Long pageCount;
        private Long blockCount;

        private Long startRow;
        private Long endRow; 
        
        private Long startPage;
        private Long endPage;
        
        private Long prevBlock;
        private Long nextBlock;
        
        private Boolean existPrev;
        private Boolean existNext;

        public PageDto(Long pageSize, Long blockSize, Long pageNumber, Long totalCount){
            this.pageSize = pageSize;
            this.blockSize = blockSize;
            this.pageNumber = pageNumber;
            this.totalCount = totalCount;

            pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
            blockCount = pageCount / blockSize + (pageCount % blockSize == 0 ? 0 : 1);

            startRow = (pageNumber - 1) * pageSize;
            endRow = pageNumber == pageCount ? totalCount - 1 : (startRow + pageSize - 1); 
        
            startPage = (blockNumber - 1) * blockSize;
            endPage = blockNumber == blockCount ? pageCount - 1 : (startPage + blockSize - 1);
        
            prevBlock = startPage - blockSize;
            nextBlock = startPage + blockSize;
        
            existPrev = prevBlock > 0;
            existNext = nextBlock < blockCount;
        }
}






