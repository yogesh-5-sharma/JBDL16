package com.example.vaccinebookingapp.controller;

import com.example.vaccinebookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public String bookAppointment(@RequestParam("user") int userId, @RequestParam("slot") int slotId) {
        return bookingService.bookSlotForUser(userId, slotId);
    }

    @PostMapping("/complete")
    public void userGotVaccine(@RequestParam("user") int userId) {
        bookingService.gotVaccine(userId);
    }

    @PostMapping("/cancel")
    public void cancelBooking(@RequestParam("user") int userId, @RequestParam("slot") int slotId) {
        bookingService.cancelSlotForUser(userId, slotId);
    }

    @GetMapping("/any")
    public String any() {
        System.out.println("Hello World");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("Bye World");
        return "Done";
    }
}
