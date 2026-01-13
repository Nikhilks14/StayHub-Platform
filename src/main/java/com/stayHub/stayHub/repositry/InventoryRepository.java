package com.stayHub.stayHub.repositry;

import com.stayHub.stayHub.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}