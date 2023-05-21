package com.crud.hotel.controller;

import com.crud.hotel.dto.PersonDTO;
import com.crud.hotel.entity.Hotel;
import com.crud.hotel.entity.Person;
import com.crud.hotel.repository.PersonRepository;
import com.crud.hotel.request.PersonRequest;
import com.crud.hotel.response.BaseResponse;
import com.crud.hotel.service.PersonService;
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
@RequestMapping("persons")
@Validated
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    private final PersonService personService;

    @ApiOperation(value = "Get ALL Persons", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get All was completed successfully"),
            @ApiResponse(code = 500, message = "Internal server error for: Persons")
    } )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonDTO>> getAll() { return ResponseEntity.status(HttpStatus.OK).body(personService.getAll()); }

    @ApiOperation(value = "Create a new Person", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new Person"),
            @ApiResponse(code = 404, message = "Room not found")
    } )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<PersonDTO>> add(@RequestBody @Validated PersonRequest request) {
        PersonDTO personDTO = personService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/person/{id}")
                .buildAndExpand(personDTO.getRoomId())
                .toUri();

        return ResponseEntity.created(uri).body(BaseResponse.<PersonDTO>builder()
                .httpCode(200)
                .message("Ok")
                .response(personDTO)
                .build());
    }

    @ApiOperation(value = "Update an Person", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the Person"),
            @ApiResponse(code = 404, message = "Room or Person not found")
    } )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<PersonDTO>> update(@RequestBody @Validated PersonRequest request, @PathVariable("id") Long id) {
        PersonDTO personDTO = personService.update(request, id);

        return ResponseEntity.ok().body(
                BaseResponse.<PersonDTO>builder()
                .httpCode(200)
                .message("Ok")
                .response(personDTO)
                .build());
    }

    @ApiOperation(value = "Delete an Person", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the Person"),
            @ApiResponse(code = 404, message = "Person not found")
    } )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(BaseResponse.<Void>builder()
                .httpCode(200)
                .message("Successfully deleted the Person")
                .response(personService.delete(id))
                .build());
    }
}
