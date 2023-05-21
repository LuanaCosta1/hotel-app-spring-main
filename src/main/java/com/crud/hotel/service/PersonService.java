package com.crud.hotel.service;

import com.crud.hotel.dto.PersonDTO;
import com.crud.hotel.entity.Person;
import com.crud.hotel.entity.Room;
import com.crud.hotel.exception.RoomNotFoundException;
import com.crud.hotel.mapper.PersonMapper;
import com.crud.hotel.repository.PersonRepository;
import com.crud.hotel.repository.RoomRepository;
import com.crud.hotel.request.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;

    public PersonService(PersonRepository personRepository, RoomRepository roomRepository) {
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
    }

    public List<PersonDTO> getAll() {
        return personRepository.findAll().stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PersonDTO create(PersonRequest request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException("Room with this id " + request.getRoomId() + " does not exist"));

        Person person = personRepository.save(PersonMapper.toEntity(request));
        person.addRoom(room);

        return PersonMapper.toDto(person);
    }

    public PersonDTO update(PersonRequest person, Long id) {
        Person entity = personRepository.getById(id);
        entity.setName(person.getName());
        entity.setAge(person.getAge());
        entity.setRg(person.getRg());
        entity.setRoom(roomRepository.getById(person.getRoomId()));
        return PersonMapper.toDto(personRepository.save(entity));
    }

    public Void delete(Long id) {
        Person entity = personRepository.getById(id);
        personRepository.delete(entity);
        return null;
    }
}
