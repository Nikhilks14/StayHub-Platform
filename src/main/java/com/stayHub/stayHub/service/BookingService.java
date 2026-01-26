package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.BookingDto;
import com.stayHub.stayHub.dto.BookingRequest;
import com.stayHub.stayHub.dto.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
