package com.crud.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends EntityNotFoundException{
    public RoomNotFoundException (String message) {super(message);}
}
