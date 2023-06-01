package com.example.java_web.contoller;

import com.example.java_web.dto.SupplyDto;
import com.example.java_web.entity.InPlace;
import com.example.java_web.entity.Person;
import com.example.java_web.entity.Place;
import com.example.java_web.service.InPlaceService;
import com.example.java_web.service.PersonService;
import com.example.java_web.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/add_data")
public class DataController {

    private final PersonService personService;
    private final PlaceService placeService;
    private final InPlaceService inPlaceService;

    public DataController(PersonService personService, PlaceService placeService, InPlaceService inPlaceService) {
        this.personService = personService;
        this.placeService = placeService;
        this.inPlaceService = inPlaceService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addData(@RequestBody SupplyDto supplyDto) {
        Person person = convertToPerson(supplyDto);
        Place place = convertToPlace(supplyDto);
        InPlace inPlace = new InPlace(person, place);

//        personService.save(person);
//        placeService.save(place);
//        inPlaceService.save(inPlace);
        person.setId(personService.insertOnConflict(person));
        place.setId(placeService.insertOnConflict(place));
        inPlaceService.insertOnConflict(inPlace);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(SupplyDto supplyDto) {
        Person person = new Person();
        person.setName(supplyDto.getNamePerson());
        person.setAge(supplyDto.getAgePerson());
        person.setSex(supplyDto.getSexPerson());
        person.setDocument(supplyDto.getDocumentPerson());

        return person;
    }

    private Place convertToPlace(SupplyDto supplyDto) {
        Place place = new Place();
        place.setName(supplyDto.getNamePlace());
        place.setLocationX(supplyDto.getLocationPlace().getX());
        place.setLocationY(supplyDto.getLocationPlace().getY());

        return place;
    }
}

