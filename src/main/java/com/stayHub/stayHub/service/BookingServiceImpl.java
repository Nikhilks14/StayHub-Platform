package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.BookingDto;
import com.stayHub.stayHub.dto.BookingRequest;
import com.stayHub.stayHub.dto.GuestDto;
import com.stayHub.stayHub.entity.*;
import com.stayHub.stayHub.entity.enums.BookingStaus;
import com.stayHub.stayHub.exception.ResoureceNotFoundException;
import com.stayHub.stayHub.repositry.BookingRepository;
import com.stayHub.stayHub.repositry.HotelRepository;
import com.stayHub.stayHub.repositry.InventoryRepository;
import com.stayHub.stayHub.repositry.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingDto initialiseBooking(BookingRequest bookingRequest) {

        log.info("Initialising booking for hotel : {}, date {} - {}",
                bookingRequest.getHotelId(),
                bookingRequest.getCheckInDate(),
                bookingRequest.getCheckOutDate());

        Hotel hotel = hotelRepository.findById(bookingRequest.getHotelId())
                .orElseThrow(() -> new ResoureceNotFoundException("Hotel Not Found with id:"  + bookingRequest.getHotelId()));

        Room room = roomRepository.findById(bookingRequest.getRoomId())
                .orElseThrow(() -> new ResoureceNotFoundException("Room Not Found with id:"  + bookingRequest.getRoomId()));

        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailaleInventory(
                room.getId(),
                bookingRequest.getCheckInDate(),
                bookingRequest.getCheckOutDate(),
                bookingRequest.getRoomCount()
        );

        System.out.println("inventory size  - "  + inventoryList.size());

        long daysCount = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate()) + 1;
        System.out.println("Number of days " + daysCount);

        if(inventoryList.size() != daysCount) {
            throw  new IllegalStateException("Room is not available anymore");
        }

        // Reserve the room/ updare the booked count of inventories
        for(Inventory inventory : inventoryList) {
            inventory.setReservedCount(inventory.getReservedCount() + bookingRequest.getRoomCount());
        }

        inventoryRepository.saveAll(inventoryList);

        // create the Booking

        User user = new User();
        user.setId(1L);

        // TODO : calculate dynamic amount

        Booking booking = Booking.builder()
                .bookingStaus(BookingStaus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .user(user)
                .roomCount(bookingRequest.getRoomCount())
                .amount(BigDecimal.TEN)
                .build();

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);


    }

    @Override
    public BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList) {
        return null;
    }
}
