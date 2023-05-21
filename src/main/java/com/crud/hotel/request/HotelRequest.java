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
public class HotelRequest {
    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Integer stars;
}
