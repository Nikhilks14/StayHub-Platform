package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}