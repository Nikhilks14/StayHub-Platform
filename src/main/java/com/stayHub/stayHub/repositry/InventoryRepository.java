package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Inventory;
import com.stayHub.stayHub.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByRoom(Room room);
}