package com.crud.hotel.exception;

import com.crud.hotel.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler({HotelNotFoundException.class, RoomNotFoundException.class, PersonNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleHotelNotFound(EntityNotFoundException e) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }
}
