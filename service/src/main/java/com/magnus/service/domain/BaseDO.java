package com.magnus.service.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class BaseDO {

    @NotBlank(message = "string1 is required")
    private String stringField1;

    @NotBlank(message = "string2 is required")
    private String stringField2;

    @NotNull(message = "longField1 is required")
    private Long longField1;

    private Long longField2;


}
