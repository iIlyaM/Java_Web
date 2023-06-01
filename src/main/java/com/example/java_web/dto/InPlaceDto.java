package com.example.java_web.dto;

import com.example.java_web.entity.Person;
import com.example.java_web.entity.Place;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class InPlaceDto {

    private Person person;

    private Place place;
}
