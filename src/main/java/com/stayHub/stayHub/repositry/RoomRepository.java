package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}