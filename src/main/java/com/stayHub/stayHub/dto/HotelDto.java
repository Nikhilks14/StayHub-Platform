package com.stayHub.stayHub.dto;

import com.stayHub.stayHub.entity.HotelContactInfo;
import com.stayHub.stayHub.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class HotelDto {

    private Long id;
    private String name;
    private String city;
    private String[] photo;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
    private List<Room> rooms;
}
