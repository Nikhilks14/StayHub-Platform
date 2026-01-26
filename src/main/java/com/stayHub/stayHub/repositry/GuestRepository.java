package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
