package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
