package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.HotelDto;
import com.stayHub.stayHub.dto.HotelSearchRequest;
import com.stayHub.stayHub.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room roomId);
    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
