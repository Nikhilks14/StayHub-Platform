package com.stayHub.stayHub.strategy;

import com.stayHub.stayHub.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
       BigDecimal price  = wrapped.calculatePrice(inventory);
       double occupanceyRate = (double) inventory.getBookedCount() / inventory.getTotalCount();
       if (occupanceyRate > 0.8) {
           price = price.multiply(BigDecimal.valueOf(1.2));
       }
        return price;
    }
}
