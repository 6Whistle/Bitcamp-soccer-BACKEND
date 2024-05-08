package com.erichgamma.api.common.service.impl;

import com.erichgamma.api.common.model.PageDto;
import com.erichgamma.api.common.service.PageService;
import com.erichgamma.api.stadium.controller.StadiumRouter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@AllArgsConstructor
@Service
public class PageServiceImpl implements PageService {
   private final StadiumRouter stadiumRouter;

    public PageDto getPageDto(Pageable page) {





        return new PageDto(Long.valueOf(page.getPageSize()), stadiumRouter.countAllStadiums(), Long.valueOf(page.getPageNumber()));


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
