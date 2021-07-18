package com.example.vaccinebookingapp.repository;

import com.example.vaccinebookingapp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("FROM Booking b WHERE b.user.userId = :userId AND b.slot.slotId = :slotId AND b.completionStatus = 'PENDING'")
    List<Booking> findPendingBookingByUseridAndSlotid(int userId, int slotId);

    @Query("FROM Booking b WHERE b.slot.date = :date AND b.completionStatus = 'PENDING'")
    List<Booking> findPendingBookingsByDate(Date date);
}
