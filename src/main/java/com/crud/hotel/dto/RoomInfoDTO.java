package com.crud.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfoDTO {
    private String description;
    private Integer number;
    private Integer floor;
    private Double price;
    private Integer bed;
    private Boolean isVip;
    private Long hotelId;
}
