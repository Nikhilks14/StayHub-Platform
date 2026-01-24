package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.RoomDto;
import com.stayHub.stayHub.entity.Hotel;
import com.stayHub.stayHub.entity.Room;
import com.stayHub.stayHub.exception.ResoureceNotFoundException;
import com.stayHub.stayHub.repositry.HotelRepository;
import com.stayHub.stayHub.repositry.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creatin a new room in hotel with Id: {}" + hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow( ()-> new ResoureceNotFoundException("Hotel not found with ID:" + hotelId));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        // TODO : create inventory as soon as is created and if hotel is active - DONE
        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomInHotel(Long hotelId) {
        log.info("Getting all room in hotel with Id: {}" + hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow( ()-> new ResoureceNotFoundException("Hotel not found with ID:" + hotelId));

        return hotel.getRooms()
                .stream()
                .map((element)-> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting all room in hotel with Id: {}" + roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow( ()-> new ResoureceNotFoundException("Room not found with ID:" + roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room in hotel with ID: {}" + roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow( ()-> new ResoureceNotFoundException("Room not found with ID:" + roomId));

        // TODO : delete all future inventory for this room
        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);
    }
}
