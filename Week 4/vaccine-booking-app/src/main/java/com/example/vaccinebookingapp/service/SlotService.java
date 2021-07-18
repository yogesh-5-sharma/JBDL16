package com.example.vaccinebookingapp.service;

import com.example.vaccinebookingapp.models.Location;
import com.example.vaccinebookingapp.models.Slot;
import com.example.vaccinebookingapp.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    SlotRepository slotRepository;

    public Slot getSlotById(int slotId) {

        Slot slot =  slotRepository.findById(slotId)
                .orElseThrow();

        return slot;
    }

    public void createOfUpdateSlot(Slot slot) {
        slotRepository.save(slot);
    }

    public List<Slot> getSlotsByDate(Date todaysDate) {
        return slotRepository.findSlotsByDate(todaysDate);
    }

    public List<Slot> getSlotByLocationAndDate(Location location, Date date) {
        List<Slot> slot = slotRepository.findByLocationAndDate(location, date);
        return slot;
    }
}
