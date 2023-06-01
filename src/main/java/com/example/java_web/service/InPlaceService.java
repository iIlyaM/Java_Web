package com.example.java_web.service;

import com.example.java_web.entity.InPlace;
import com.example.java_web.repository.InPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InPlaceService {

    private final InPlaceRepository inPlaceRepository;

    @Autowired
    public InPlaceService(InPlaceRepository inPlaceRepository) {
        this.inPlaceRepository = inPlaceRepository;
    }

    public List<InPlace> getAllInPlace() {
        return inPlaceRepository.findAll();
    }

    @Transactional
    public void save(InPlace inPlace) {
        inPlaceRepository.save(inPlace);
    }

    @Transactional
    public Integer insertOnConflict(InPlace inPlace) {
        return inPlaceRepository.insertOnConflict(inPlace.getPerson().getId(), inPlace.getPlace().getId());
    }

    @Transactional
    public void deleteAll() {
        inPlaceRepository.deleteAll();
    }

}
