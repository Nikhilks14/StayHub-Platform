package com.stayHub.stayHub.controller;

import com.stayHub.stayHub.dto.BookingDto;
import com.stayHub.stayHub.dto.BookingRequest;
import com.stayHub.stayHub.dto.GuestDto;
import com.stayHub.stayHub.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initiateBooking(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuests(@PathVariable("bookingId") Long bookingId,
                                                @RequestBody List<GuestDto>  guestDtoList) {
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDtoList));

    }

}
