package com.example.java_web.dto;

import lombok.Data;

import java.awt.*;

@Data
public class SupplyDto {
    private String namePerson;
    private Integer agePerson;
    private String sexPerson;
    private String documentPerson;
    private String namePlace;
    private Point locationPlace;
}