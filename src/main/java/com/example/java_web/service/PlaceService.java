package com.example.java_web.service;

import com.example.java_web.entity.Place;

import com.example.java_web.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place getPlaceByName(String name) {
        return placeRepository.findByName(name);
    }

    @Transactional
    public void save(Place place) {
        placeRepository.save(place);
    }

    @Transactional
    public void savePersons(List<Place> places) {
        placeRepository.saveAll(places);
    }

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    @Transactional
    public Integer insertOnConflict(Place place){
        return placeRepository.insertOnConflict(place.getName(), place.getLocationX(), place.getLocationY());
    }

    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteByName(String name) {
        placeRepository.deleteByName(name);
    }
}
