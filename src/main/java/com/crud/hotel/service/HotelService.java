package com.crud.hotel.service;

import com.crud.hotel.dto.HotelDTO;
import com.crud.hotel.dto.HotelRoomsDTO;
import com.crud.hotel.entity.Hotel;
import com.crud.hotel.mapper.HotelMapper;
import com.crud.hotel.repository.HotelRepository;
import com.crud.hotel.repository.RoomRepository;
import com.crud.hotel.request.HotelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    public List<HotelRoomsDTO> getAll() {
        return hotelRepository.findAll().stream()
                .map(HotelMapper::toHotelRoomsDTO)
                .collect(Collectors.toList());
    }

    public Hotel create(HotelRequest request) {
        return hotelRepository.save(HotelMapper.toEntity(request));
    }

    public HotelDTO update(HotelRequest hotel, Long id) {
        Hotel entity = hotelRepository.getById(id);
        entity.setName(hotel.getName());
        entity.setAddress(hotel.getAddress());
        entity.setStars(hotel.getStars());
        entity.setRoomList(entity.getRoomList());
        return HotelMapper.toDto(hotelRepository.save(entity));
    }

    public Void delete(Long id) {
        Hotel entity = hotelRepository.getById(id);
        hotelRepository.delete(entity);
        return null;
    }
}
