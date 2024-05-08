package com.erichgamma.api.common.model;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Box {
    private PageDto pageDto;
    private List<?> listDto;
}
