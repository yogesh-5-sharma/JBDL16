package com.example.vaccinebookingapp.controller;

import com.example.vaccinebookingapp.models.Location;
import com.example.vaccinebookingapp.models.Slot;
import com.example.vaccinebookingapp.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    SlotService slotService;

    @GetMapping("/{slotId}")
    public Slot getLocation(@PathVariable int slotId) {
        return slotService.getSlotById(slotId);
    }
}
