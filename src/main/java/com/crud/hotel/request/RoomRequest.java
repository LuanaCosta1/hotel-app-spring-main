package com.crud.hotel.request;

import com.crud.hotel.entity.Hotel;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

    @NotNull
    private String description;

    @NotNull
    private Integer number;

    @NotNull
    private Integer floor;

    @NotNull
    private Double price;

    @NotNull
    private Integer bed;

    @NotNull
    private Boolean isVip;

    @NotNull
    private Long hotelId;
}
