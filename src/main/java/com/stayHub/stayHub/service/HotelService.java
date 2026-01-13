package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.HotelDto;
import com.stayHub.stayHub.entity.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
}
