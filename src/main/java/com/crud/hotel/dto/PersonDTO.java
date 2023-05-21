package com.crud.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private String name;
    private Integer age;
    private Double rg;
    private Long roomId;
    private RoomDTO room;
}
