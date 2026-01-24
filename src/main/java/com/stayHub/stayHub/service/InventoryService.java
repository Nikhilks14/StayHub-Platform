package com.stayHub.stayHub.service;

import com.stayHub.stayHub.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room roomId);
    void deleteAllInventories(Room room);
}
