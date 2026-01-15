package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Inventory;
import com.stayHub.stayHub.entity.Room;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}