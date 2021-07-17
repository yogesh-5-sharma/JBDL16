package com.example.vaccinebookingapp.controller;

import com.example.vaccinebookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public String bookAppointment(@RequestParam("user") int userId, @RequestParam("slot") int slotId) {
        return bookingService.bookSlotForUser(userId, slotId);
    }
}
