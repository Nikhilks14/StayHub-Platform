package com.stayHub.stayHub.service;

import com.stayHub.stayHub.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room roomId);
    void deleteFutureInventories(Room room);
}
