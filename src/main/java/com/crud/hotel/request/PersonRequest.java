package com.crud.hotel.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Double rg;

    @NotNull
    private Long roomId;
}
