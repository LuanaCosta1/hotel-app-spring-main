package com.crud.hotel.controller;

import com.crud.hotel.dto.RoomDTO;
import com.crud.hotel.dto.RoomPersonsDTO;
import com.crud.hotel.repository.RoomRepository;
import com.crud.hotel.request.RoomRequest;
import com.crud.hotel.response.BaseResponse;
import com.crud.hotel.service.RoomService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    private final RoomService roomService;

    @ApiOperation(value = "Get ALL Rooms", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get All was completed successfully"),
            @ApiResponse(code = 500, message = "Internal server error for: Rooms")
    } )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomPersonsDTO>> getAll() { return ResponseEntity.status(HttpStatus.OK).body(roomService.getAll()); }

    @ApiOperation(value = "Create a new Room", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new Room"),
            @ApiResponse(code = 404, message = "Hotel not found")
    } )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<RoomDTO>> add(@RequestBody @Validated RoomRequest request) {
        RoomDTO room = roomService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/person/{id}")
                .buildAndExpand(room.getHotelId())
                .toUri();

        return ResponseEntity.created(uri).body(BaseResponse.<RoomDTO>builder()
                .httpCode(200)
                .message("Ok")
                .response(room)
                .build());
    }

    @ApiOperation(value = "Update an Room", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the Room"),
            @ApiResponse(code = 404, message = "Room or Hotel not found")
    } )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<RoomDTO>> update(@RequestBody @Validated RoomRequest request, @PathVariable("id") Long id) {
        RoomDTO room = roomService.update(request, id);

        return ResponseEntity.ok().body(BaseResponse.<RoomDTO>builder()
                .httpCode(200)
                .message("Ok")
                .response(room)
                .build());
    }

    @ApiOperation(value = "Delete an Room", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the Room"),
            @ApiResponse(code = 200, message = "Successfully deleted the Room but returned No Content"),
            @ApiResponse(code = 404, message = "Room not found")
    } )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(BaseResponse.<Void>builder()
                .httpCode(200)
                .message("Successfully deleted the Room")
                .response(roomService.delete(id))
                .build());
    }
}
