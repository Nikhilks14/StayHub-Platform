package com.stayHub.stayHub.strategy;

import com.stayHub.stayHub.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {
    public BigDecimal calculateDynamicPricing(Inventory inventory) {

        PricingStrategy pricingStrategy = new BasePriceStrategy();

        // apply the additional strategies

        pricingStrategy = new SurgePriceStrategy(pricingStrategy);
        pricingStrategy = new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy = new HolidayPricingstrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);

    }
}
