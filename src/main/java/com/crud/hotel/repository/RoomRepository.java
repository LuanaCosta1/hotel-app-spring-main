package com.crud.hotel.repository;

import com.crud.hotel.entity.Person;
import com.crud.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room getById(Long id);
}
