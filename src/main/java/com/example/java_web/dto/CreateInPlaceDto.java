package com.example.java_web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateInPlaceDto {
    private Integer personId;

    private Integer placeId;
}
