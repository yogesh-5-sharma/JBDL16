package com.example.vaccinebookingapp.repository;

import com.example.vaccinebookingapp.models.Location;
import com.example.vaccinebookingapp.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

    @Query("FROM Slot s WHERE s.date = :date")
    List<Slot> findSlotsByDate(Date date);

//    @Query("FROM Slot s WHERE s.location = :location AND s.date = :date")
    List<Slot> findByLocationAndDate(Location location, Date date);
}
