package com.erichgamma.api.common.service.impl;

import com.erichgamma.api.common.model.PageDTO;
import com.erichgamma.api.common.service.PageService;
import com.erichgamma.api.player.model.PlayerDto;
import com.erichgamma.api.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {



    public PageDTO getPageDTO(Long pageSize, Long totalCount ,Long pageNumber){


         Long blockCount ;
         Long pageCount ;
         Long startRow ;
         Long endRow ;
         Long blockNum ;
         Long startPage ;
         Long endPage ;
         Long nextBlock ;
         Long prevBlock ;
         Long BLOCK_SIZE = 10L;
         Boolean existPrev ;
         Boolean existNext ;

        pageCount =  ( (totalCount%pageSize) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1);
        blockCount =  ( (totalCount%(pageSize*BLOCK_SIZE)) == 0 ? totalCount/(pageSize*BLOCK_SIZE) : (totalCount/(pageSize*BLOCK_SIZE))+1);
        blockNum = pageNumber % BLOCK_SIZE == 0 ? pageNumber/ BLOCK_SIZE :  pageNumber/ BLOCK_SIZE +1;
        startRow =  pageSize*(pageNumber-1) ;
        endRow = pageNumber == pageCount ? (Long) (totalCount - 1) :  startRow +pageSize-1;
        startPage = BLOCK_SIZE*(blockNum-1)+1;
        endPage = blockNum == blockCount ? pageCount -1 : startPage+BLOCK_SIZE-1;
        nextBlock = blockNum+1;
        prevBlock = blockNum -1;
        existPrev = prevBlock != 0;
        existNext = nextBlock != blockCount;







       return new PageDTO(pageSize, totalCount,pageNumber);

//        log.info("MY-INFO : Controller searchPlayer totalCount is {}", totalCount);
//        log.info("MY-INFO : Controller searchPlayer pageCount is {}", pageCount);
//        log.info("MY-INFO : Controller searchPlayer blockCount is {}", blockCount);
//        log.info("MY-INFO : Controller searchPlayer startRow is {}", startRow);
//        log.info("MY-INFO : Controller searchPlayer endRow is {}", endRow);
//        log.info("MY-INFO : Controller searchPlayer blockNum is {}", blockNum);
//        log.info("MY-INFO : Controller searchPlayer startPage is {}", startPage);
//        log.info("MY-INFO : Controller searchPlayer endPage is {}", endPage);
//        log.info("MY-INFO : Controller searchPlayer existPrev is {}", existPrev);
//        log.info("MY-INFO : Controller searchPlayer existNext is {}", existNext);
//        log.info("MY-INFO : Controller searchPlayer nextBlock is {}", nextBlock);
//        log.info("MY-INFO : Controller searchPlayer prevBlock is {}", prevBlock);
//        log.info("MY-INFO : Controller searchPlayer pageNumber is {}", pageNumber);



    }
}
