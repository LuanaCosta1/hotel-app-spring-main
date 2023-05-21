package com.crud.hotel.controller;

import com.crud.hotel.dto.ErrorDTO;
import com.crud.hotel.dto.HotelDTO;
import com.crud.hotel.dto.HotelRoomsDTO;
import com.crud.hotel.entity.Hotel;
import com.crud.hotel.exception.CustomErrorHandler;
import com.crud.hotel.repository.HotelRepository;
import com.crud.hotel.request.HotelRequest;
import com.crud.hotel.response.BaseResponse;
import com.crud.hotel.service.HotelService;
import io.swagger.annotations.Api;
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

@Api(tags = "Hotel Controller", consumes = "application/json")
@RestController
@AllArgsConstructor
@RequestMapping("hotels")
@Validated
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    private final HotelService hotelService;

    @ApiOperation(value = "Get ALL Hotels", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get All was completed successfully"),
            @ApiResponse(code = 500, message = "Internal server error for: Hotels")
    } )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HotelRoomsDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    };

    @ApiOperation(value = "Create a new Hotel", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new Hotel"),
            @ApiResponse(code = 404, message = "Room or Person not found")
    } )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<Hotel>> add(@RequestBody @Validated HotelRequest request) {
        Hotel hotel = hotelService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/hotel/{id}")
                .buildAndExpand(hotel.getId())
                .toUri();

        return ResponseEntity.created(uri).body(BaseResponse.<Hotel>builder()
                .httpCode(200)
                .message("Ok")
                .response(hotel)
                .build());
    }

    @ApiOperation(value = "Update an Hotel", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the Hotel"),
            @ApiResponse(code = 404, message = "Hotel, Room or Person not found")
    } )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<HotelDTO>> update(@RequestBody HotelRequest request, @PathVariable("id") Long id) {
        HotelDTO hotel = hotelService.update(request,id);

        return ResponseEntity.ok().body(BaseResponse.<HotelDTO>builder()
                .httpCode(200)
                .message("Ok")
                .response(hotel)
                .build());
    }

    @ApiOperation(value = "Delete an Hotel", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the Hotel"),
            @ApiResponse(code = 204, message = "Successfully deleted the Hotel but returned No Content"),
            @ApiResponse(code = 404, message = "Hotel not found")
    } )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(BaseResponse.<Void>builder()
                .httpCode(200)
                .message("Successfully deleted the Hotel")
                .response(hotelService.delete(id))
                .build());
    }
}


