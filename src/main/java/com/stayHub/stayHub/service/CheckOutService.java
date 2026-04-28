package com.stayHub.stayHub.service;

import com.stayHub.stayHub.entity.Booking;

public interface CheckOutService {
    String getCheckOutSession(Booking booking, String successUrl, String failureUrl);
}
