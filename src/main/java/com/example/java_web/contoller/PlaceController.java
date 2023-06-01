package com.example.java_web.contoller;

import com.example.java_web.dto.PlaceDto;
import com.example.java_web.entity.Place;
import com.example.java_web.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public List<PlaceDto> getPlaces() {
        return placeService.getPlaces().stream().map(this::convertToPlaceDto).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public PlaceDto getPlace(@PathVariable("name") String name) {
        return convertToPlaceDto(placeService.getPlaceByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody PlaceDto placeDto) {
        placeService.save(convertToPlace(placeDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody PlaceDto placeDto) {
        placeService.deleteByName(convertToPlace(placeDto).getName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Place convertToPlace(PlaceDto placeDto) {
        Place place = new Place();
        place.setName(placeDto.getName());
        place.setLocationX(placeDto.getPoint().getX());
        place.setLocationY(placeDto.getPoint().getY());

        return place;
    }

    private PlaceDto convertToPlaceDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(place.getId());
        placeDto.setName(place.getName());
        placeDto.setPoint(new Point(place.getLocationX(), place.getLocationY()));

        return placeDto;
    }

}
