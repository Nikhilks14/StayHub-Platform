package com.stayHub.stayHub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stayHub.stayHub.entity.Hotel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDto {
    private Long id;
    @JsonIgnore
    private Hotel hotel;
    private String type;
    private BigDecimal basePrice;
    private String[] photo;
    private String[] amenities;
    private String totalCount;
    private String capacity;
}
