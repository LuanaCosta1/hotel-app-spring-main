package com.crud.hotel.mapper;

import com.crud.hotel.dto.RoomDTO;
import com.crud.hotel.dto.RoomPersonsDTO;
import com.crud.hotel.entity.Room;
import com.crud.hotel.request.RoomRequest;

import java.util.stream.Collectors;

public class RoomMapper {
    public static Room toEntity(RoomRequest request) {
        return Room.builder()
                .description(request.getDescription())
                .number(request.getNumber())
                .floor(request.getFloor())
                .price(request.getPrice())
                .bed(request.getBed())
                .isVip(request.getIsVip())
                .build();
    }

    public static RoomPersonsDTO toRoomPersonsDTO(Room room) {
        return RoomPersonsDTO.builder()
                .description(room.getDescription())
                .number(room.getNumber())
                .floor(room.getFloor())
                .price(room.getPrice())
                .bed(room.getBed())
                .isVip(room.getIsVip())
                .hotelId(room.getHotel().getId())
                .persons(room.getPersonList().stream().map(PersonMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    public static RoomDTO toDto(Room room) {
        return RoomDTO.builder()
                .description(room.getDescription())
                .number(room.getNumber())
                .floor(room.getFloor())
                .price(room.getPrice())
                .bed(room.getBed())
                .isVip(room.getIsVip())
                .hotelId(room.getHotel().getId())
                .hotel(HotelMapper.toDto(room.getHotel()))
                .build();
    }
}
