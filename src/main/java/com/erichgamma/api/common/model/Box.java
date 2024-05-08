package com.erichgamma.api.common.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Builder
public class Box {
    private PageDto pageDto;
    private List<?> list;
}
