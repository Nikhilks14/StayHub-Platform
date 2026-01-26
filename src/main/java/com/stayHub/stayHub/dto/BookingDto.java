package com.stayHub.stayHub.dto;

import com.stayHub.stayHub.entity.Guest;
import com.stayHub.stayHub.entity.Hotel;
import com.stayHub.stayHub.entity.Room;
import com.stayHub.stayHub.entity.User;
import com.stayHub.stayHub.entity.enums.BookingStaus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;
//    private Hotel hotel;
//    private Room room;
//    private User user;
    private Integer roomCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStaus bookingStaus;
    private Set<GuestDto> guests;
}
