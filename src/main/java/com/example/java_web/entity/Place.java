package com.example.java_web.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Entity
@Table(name = "places")
@NoArgsConstructor
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @Column(name = "location_x")
    private Double locationX;

    @Column(name = "location_y")
    private Double locationY;

    public Place(Integer id, String name, Double locationX, Double locationY) {
        this.id = id;
        this.name = name;
        this.locationX = locationX;
        this.locationY = locationY;
    }
}
