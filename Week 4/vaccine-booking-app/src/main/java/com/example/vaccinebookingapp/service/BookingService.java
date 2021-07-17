package com.example.vaccinebookingapp.service;

import com.example.vaccinebookingapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public String bookSlotForUser(int userId, int slotId) {

        // user and slot exist
        // slot with given id is old
        // age limit
        // check for previous appointments
        // - whether user is already vaccinated
        // - PENDING appointment
        // - check for previous vaccine and book the same one
        // - check for minimum number of days
        // see whether vaccines are available in the slot, whether slot is filled or have some vaccines

        return "";
    }

    public void cancelSlotForUser(int userId, int slotId) {

        // check whether user has really booked the slot, PENDING status

    }
}
