package com.stayHub.stayHub.strategy;

import com.stayHub.stayHub.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class HolidayPricingstrategy implements PricingStrategy {

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {

        BigDecimal price = calculatePrice(inventory);
        boolean isTodayHoliday = true; // call an API or check with local date

        if(isTodayHoliday){
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;
    }

}
