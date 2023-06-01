package com.example.java_web.contoller;

import com.example.java_web.dto.CreateInPlaceDto;
import com.example.java_web.dto.InPlaceDto;
import com.example.java_web.entity.InPlace;
import com.example.java_web.service.InPlaceService;
import com.example.java_web.service.PersonService;
import com.example.java_web.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/in_place")
public class InPlaceController {
    private final InPlaceService inPlaceService;
    private final PlaceService placeService;
    private final PersonService personService;

    @Autowired
    public InPlaceController(InPlaceService inPlaceService, PlaceService placeService, PersonService personService) {
        this.inPlaceService = inPlaceService;
        this.placeService = placeService;
        this.personService = personService;
    }

    @GetMapping()
    public List<InPlaceDto> getInPlaces() {
        return inPlaceService.getAllInPlace().stream().map(this::convertToInPlaceDto).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody CreateInPlaceDto inPlaceDto) {
        inPlaceService.save(convertToInPlace(inPlaceDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private InPlace convertToInPlace(CreateInPlaceDto inPlaceDto) {
        InPlace inPlace = new InPlace();

        inPlace.setPerson(personService.getPersonById(inPlaceDto.getPersonId()));
        inPlace.setPlace(placeService.getPlaceById(inPlaceDto.getPlaceId()));

        return inPlace;
    }

    private InPlaceDto convertToInPlaceDto(InPlace inPlace) {
        InPlaceDto inPlaceDto = new InPlaceDto();
        inPlaceDto.setPerson(inPlace.getPerson());
        inPlaceDto.setPlace(inPlace.getPlace());

        return inPlaceDto;
    }
}
