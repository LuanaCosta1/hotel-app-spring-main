package com.crud.hotel.service;

import com.crud.hotel.dto.RoomDTO;
import com.crud.hotel.dto.RoomPersonsDTO;
import com.crud.hotel.entity.Hotel;
import com.crud.hotel.entity.Room;
import com.crud.hotel.exception.HotelNotFoundException;
import com.crud.hotel.mapper.RoomMapper;
import com.crud.hotel.repository.HotelRepository;
import com.crud.hotel.repository.PersonRepository;
import com.crud.hotel.repository.RoomRepository;
import com.crud.hotel.request.RoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final PersonRepository personRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository, PersonRepository personRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.personRepository = personRepository;
    }

    public List<RoomPersonsDTO> getAll() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::toRoomPersonsDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomDTO create(RoomRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel with this id " + request.getHotelId() + " does not exist"));

        Room room = roomRepository.save(RoomMapper.toEntity(request));
        room.addHotel(hotel);

        return RoomMapper.toDto(room);
    }

    public RoomDTO update(RoomRequest room, Long id) {
        Room entity = roomRepository.getById(id);
        entity.setDescription(room.getDescription());
        entity.setNumber(room.getNumber());
        entity.setFloor(room.getFloor());
        entity.setPrice(room.getPrice());
        entity.setBed(room.getBed());
        entity.setIsVip(room.getIsVip());
        entity.setHotel(hotelRepository.getById(room.getHotelId()));
        entity.setPersonList(personRepository.findAll());
        return RoomMapper.toDto(roomRepository.save(entity));
    }

    public Void delete(Long id) {
        Room entity = roomRepository.getById(id);
        roomRepository.delete(entity);
        return null;
    }
}
