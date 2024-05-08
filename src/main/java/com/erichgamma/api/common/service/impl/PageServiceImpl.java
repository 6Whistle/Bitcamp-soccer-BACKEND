package com.erichgamma.api.common.service.impl;

import com.erichgamma.api.common.model.PageDto;
import com.erichgamma.api.common.service.PageService;
import com.erichgamma.api.stadium.controller.StadiumRouter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class PageServiceImpl implements PageService {
    private final StadiumRouter stadiumRouter;

    public PageDto getPageDto(Pageable page) {
        Long pageSize = Long.valueOf(page.getPageSize());
        Long totalCount = stadiumRouter.countAllStadiums();
        Long pageNumber = Long.valueOf(page.getPageNumber());

        Long BLOCK_SIZE = 10L;
        Long blockCount;
        Long pageCount;
        Long startRow;
        Long endRow;
        Long startPage;
        Long endPage;
        Long nextBlock;
        Long prevBlock;
        Long blockNumber;
        Boolean existPrev;
        Boolean existNext;

        pageCount = (totalCount % pageSize == 0) ? totalCount / pageSize : (totalCount / pageSize) + 1;
        blockCount = (totalCount % (BLOCK_SIZE * pageSize) == 0) ? totalCount / (BLOCK_SIZE * pageSize) : totalCount / (BLOCK_SIZE * pageSize) + 1;

        startRow = pageSize * (pageNumber - 1);
        endRow = startRow + (pageSize - 1);

        blockNumber = (pageNumber % BLOCK_SIZE == 0) ? (pageNumber / BLOCK_SIZE) : (pageNumber / BLOCK_SIZE) + 1;

        startPage = (BLOCK_SIZE * (blockNumber - 1)) + 1;
        endPage = (startPage + BLOCK_SIZE) - 1;

        nextBlock = blockNumber + 1;
        prevBlock = blockNumber - 1;

        existPrev = prevBlock != 0 ? true : false;
        existNext = blockCount >= nextBlock ? true : false;


        return new PageDto().builder()
                .totalCount(totalCount)
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .pageCount(pageCount)
                .blockCount(blockCount)
                .startRow(startRow)
                .endRow(endRow)
                .startPage(startPage)
                .endPage(endPage)
                .nextBlock(nextBlock)
                .prevBlock(prevBlock)
                .blockNumber(blockNumber)
                .existPrev(existPrev)
                .existNext(existNext)
                .BLOCK_SIZE(BLOCK_SIZE)
                .build();

//        log.info("MY-INFO : Controller searchPlayer totalCount is {}", totalCount);
//        log.info("MY-INFO : Controller searchPlayer pageCount is {}", pageCount);
//        log.info("MY-INFO : Controller searchPlayer blockCount is {}", blockCount);
//        log.info("MY-INFO : Controller searchPlayer startRow is {}", startRow);
//        log.info("MY-INFO : Controller searchPlayer endRow is {}", endRow);
//        log.info("MY-INFO : Controller searchPlayer blockNum is {}", blockNumber);
//        log.info("MY-INFO : Controller searchPlayer startPage is {}", startPage);
//        log.info("MY-INFO : Controller searchPlayer endPage is {}", endPage);
//        log.info("MY-INFO : Controller searchPlayer existPrev is {}", existPrev);
//        log.info("MY-INFO : Controller searchPlayer existNext is {}", existNext);
//        log.info("MY-INFO : Controller searchPlayer nextBlock is {}", nextBlock);
//        log.info("MY-INFO : Controller searchPlayer prevBlock is {}", prevBlock);

    }
}
