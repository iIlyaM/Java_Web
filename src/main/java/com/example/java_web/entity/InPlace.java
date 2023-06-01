package com.example.java_web.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="in_place")
@NoArgsConstructor
@Data
public class InPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Person person;

    @OneToOne
    @JoinColumn(name="id_place", referencedColumnName = "id")
    private Place place;

    public InPlace(Person person, Place place) {
        this.person = person;
        this.place = place;
    }
}
