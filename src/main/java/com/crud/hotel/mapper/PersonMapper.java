package com.crud.hotel.mapper;

import com.crud.hotel.dto.PersonDTO;
import com.crud.hotel.dto.PersonInfoDTO;
import com.crud.hotel.entity.Person;
import com.crud.hotel.request.PersonRequest;

public class PersonMapper {
    private PersonMapper() {}

    public static PersonDTO toDto(Person person) {
        return PersonDTO.builder()
                .name(person.getName())
                .age(person.getAge())
                .rg(person.getRg())
                .roomId(person.getRoom().getId())
                .room(RoomMapper.toDto(person.getRoom()))
                .build();
    }

    public static PersonInfoDTO personDto(Person person) {
        return PersonInfoDTO.builder()
                .name(person.getName())
                .age(person.getAge())
                .rg(person.getRg())
                .roomId(person.getRoom().getId())
                .build();
    }

    public static Person toEntity(PersonRequest request) {
        return Person.builder()
                .name(request.getName())
                .age(request.getAge())
                .rg(request.getRg())
                .build();
    }
}
