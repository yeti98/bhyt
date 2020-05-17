package com.devculi.bhyt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private String code;
    private double value;
    private String valueDescription;
    private double minValue;
}
