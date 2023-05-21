package com.crud.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private String description;
    private Integer number;
    private Integer floor;
    private Double price;
    private Integer bed;
    private Boolean isVip;
    private Long hotelId;
    private HotelDTO hotel;
}
