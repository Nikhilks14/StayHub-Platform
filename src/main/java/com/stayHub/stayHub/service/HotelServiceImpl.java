package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.HotelDto;
import com.stayHub.stayHub.entity.Hotel;
import com.stayHub.stayHub.exception.ResoureceNotFoundException;
import com.stayHub.stayHub.repositry.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}" , hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}" , hotelDto.getId());
        return modelMapper.map(hotel , HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel with ID: {}" , id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow( ()-> new ResoureceNotFoundException("Hotel Not found with ID : " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating the hotel with ID: {}" , id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow( ()-> new ResoureceNotFoundException("Hotel is not found with id :" + id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if (!exists) throw new ResoureceNotFoundException("Hotel not found with id : " + id);
        hotelRepository.deleteById(id);

        // TODO : delete the future inventories for this hotel
    }

    @Override
    public void activateHotel(Long hotelId) {
        log.info("Updating the hotel with ID: {}" , hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow( ()-> new ResoureceNotFoundException("Hotel is not found with id :" + hotelId));
       hotel.setActive(true);

       // Todo : Create inventory foal all thr room for this hotel
    }




}
