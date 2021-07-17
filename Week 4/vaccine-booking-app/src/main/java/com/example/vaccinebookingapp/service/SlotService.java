package com.example.vaccinebookingapp.service;

import com.example.vaccinebookingapp.models.Slot;
import com.example.vaccinebookingapp.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotService {

    @Autowired
    SlotRepository slotRepository;

    public Slot getSlotById(int slotId) {

        Slot slot =  slotRepository.findById(slotId)
                .orElseThrow();

        return slot;
    }
}
