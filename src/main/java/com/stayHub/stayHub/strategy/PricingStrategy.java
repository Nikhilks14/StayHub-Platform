package com.stayHub.stayHub.strategy;

import com.stayHub.stayHub.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
