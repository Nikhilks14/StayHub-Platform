package com.stayHub.stayHub.dto;

import com.stayHub.stayHub.entity.Booking;
import com.stayHub.stayHub.entity.User;
import com.stayHub.stayHub.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
    private Set<Booking> bookings;
}
