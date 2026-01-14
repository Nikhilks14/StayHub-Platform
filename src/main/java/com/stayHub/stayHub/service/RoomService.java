package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(RoomDto roomDto);
    List<RoomDto> getAllRoomInHotel(Long hotelId);
    RoomDto getRoomById(Long roomId);
    void deleteRoomById(Long roomId);
}
