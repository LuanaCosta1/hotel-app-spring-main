package com.crud.hotel.repository;

import com.crud.hotel.entity.Hotel;
import com.crud.hotel.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person getById(Long id);
}
