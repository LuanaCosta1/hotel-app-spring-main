package com.crud.hotel.mapper;

import com.crud.hotel.dto.HotelDTO;
import com.crud.hotel.dto.HotelRoomsDTO;
import com.crud.hotel.entity.Hotel;
import com.crud.hotel.request.HotelRequest;

import java.util.stream.Collectors;

public class HotelMapper {
    private HotelMapper() {}

    public static HotelDTO toDto(Hotel hotel) {
        return HotelDTO.builder()
                .name(hotel.getName())
                .address(hotel.getAddress())
                .stars(hotel.getStars())
                .qtRooms(hotel.getQtRooms())
                .build();
    }

    public static HotelRoomsDTO toHotelRoomsDTO(Hotel hotel) {
        return HotelRoomsDTO.builder()
                .name(hotel.getName())
                .address(hotel.getAddress())
                .stars(hotel.getStars())
                .qtRooms(hotel.getQtRooms())
                .rooms(hotel.getRoomList().stream().map(RoomMapper::toRoomPersonsDTO).collect(Collectors.toList()))
                .build();
    }

    public static Hotel toEntity(HotelRequest request) {
        return Hotel.builder()
                .name(request.getName())
                .address(request.getAddress())
                .stars(request.getStars())
                .build();
    }
}
