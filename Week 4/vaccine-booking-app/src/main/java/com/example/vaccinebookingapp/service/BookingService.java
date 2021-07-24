package com.example.vaccinebookingapp.service;

import com.example.vaccinebookingapp.models.*;
import com.example.vaccinebookingapp.repository.BookingRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@EnableAsync
@EnableRetry
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserService userService;

    @Autowired
    SlotService slotService;

//    Map<String, Booking> // 1000

    @Transactional
    @Retryable(StaleObjectStateException.class)
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

        // user and slot exist
        User user = userService.getUserById(userId);
        Slot slot = slotService.getSlotById(slotId);

        // slot with given id is old
        if (checkIfSlotIsOld(slot)) {
            throw new IllegalArgumentException("slot you are accessing is very old");
        }

        // age limit
        if (!ageIsCorrect(user, slot)) {
            throw new IllegalArgumentException("Required Age limit is not satisfied");
        }

        List<Booking> completedBookings = user.getBookings().stream()
                .filter(booking -> booking.getCompletionStatus() == CompletionStatus.COMPLETED)
                .sorted(Comparator.comparing(booking -> booking.getSlot().getDate()))
                .collect(Collectors.toList());

        if (completedBookings.size() > 0) {

            Vaccine vaccine = completedBookings.get(0).getSlot().getVaccine();

            // whether user is already vaccinated
            if(completedBookings.size() == vaccine.getDoses()) {
                throw new IllegalArgumentException("You are already fully vaccinated. No more doses required");
            }

            // check for previous vaccine and book the same one
            if (slot.getVaccine() != vaccine) {
                throw new IllegalArgumentException("Please book the same vaccine as the last");
            }

            // check for minimum number of days
            Date lastVaccinatedDate = completedBookings.get(completedBookings.size()-1).getSlot().getDate();
            if (slot.getDate().toLocalDate().compareTo(lastVaccinatedDate.toLocalDate()) < vaccine.getMinDaysOfDifference()) {
                throw new IllegalArgumentException("Min days of difference is not completed");
            }
        }

        // PENDING appointment
        boolean isPendingAppointment = user.getBookings().stream()
                .anyMatch(booking -> booking.getCompletionStatus() == CompletionStatus.PENDING);

        if (isPendingAppointment) {
            throw new IllegalArgumentException("You already have a pending appointment, please complete that before booking another");
        }

        return book(userId, slotId);

    }

    @Transactional
    private String book(int userId, int slotId) {
        Slot slot = slotService.getSlotById(slotId);
        User user = userService.getUserById(userId);
        // see whether vaccines are available in the slot, whether slot is filled or have some vaccines
        if (slot.getCount() <=0) {
            throw new IllegalArgumentException("All the vaccines in this slot are already booked");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // all conditions are satisfied
        slot.setCount(slot.getCount() - 1);
        slotService.createOfUpdateSlot(slot);

//        String newUUID = UUID.randomUUID().toString();
        Booking newBooking = Booking.builder()
                .referenceId(UUID.randomUUID().toString())
                .slot(slot)
                .user(user)
                .completionStatus(CompletionStatus.PENDING)
                .build();

        Booking finalBooking = bookingRepository.save(newBooking);

        return finalBooking.getReferenceId();
    }

    public void gotVaccine(int userId) {

        User user = userService.getUserById(userId);

        List<Booking> pendingBookings = user.getBookings().stream()
                .filter(booking -> booking.getCompletionStatus() == CompletionStatus.PENDING)
                .collect(Collectors.toList());

        if (pendingBookings.size() == 0) {
            throw new IllegalArgumentException("No Pending booking that exist.");
        }
        if (pendingBookings.size() != 1) {
            throw new IllegalArgumentException("Illegal state with size " + pendingBookings.size());
        }

        // Check whether you got vaccine on the same day, not in future.
        LocalDate futureDate = pendingBookings.get(0).getSlot().getDate().toLocalDate();
        LocalDate todaysDate = LocalDate.now();
        if(todaysDate.compareTo(futureDate) != 0) {
            throw new IllegalArgumentException("You got vaccinated in future how :)");
        }

        pendingBookings.get(0).setCompletionStatus(CompletionStatus.COMPLETED);
        bookingRepository.save(pendingBookings.get(0));
    }

    public void cancelSlotForUser(int userId, int slotId) {

        // check whether user has really booked the slot, PENDING status

        List<Booking> pendingBookings = bookingRepository.findPendingBookingByUseridAndSlotid(userId, slotId);

        if (pendingBookings.size() == 0) {
            throw new IllegalArgumentException("No Pending Bookings are there to cancel");
        }
        if (pendingBookings.size() != 1) {
            throw new IllegalArgumentException("Illegal state with size " + pendingBookings.size());
        }

        // increment the count of vaccines in slot
        Slot slot = pendingBookings.get(0).getSlot();
        slot.setCount(slot.getCount() + 1);
        slotService.createOfUpdateSlot(slot);

        // changing status from PENDING to CANCELLED
        pendingBookings.get(0).setCompletionStatus(CompletionStatus.CANCELLED);
        bookingRepository.save(pendingBookings.get(0));

    }

    // cancel all bookings for todays whose status is PENDING, and user didn't came for vaccine
    // and shifting those vaccines for tomorrow
    @Scheduled(cron = "0 28 20 * * *")
    @Async
    public void cancelBookingForToday() {

        Date todaysDate = Date.valueOf(LocalDate.now());

        List<Booking> pendingBookings = bookingRepository.findPendingBookingsByDate(todaysDate);

        for(Booking pendingBooking: pendingBookings) {
            pendingBooking.setCompletionStatus(CompletionStatus.CANCELLED);
            Slot slot = pendingBooking.getSlot();
            slot.setCount(slot.getCount() + 1);
            slotService.createOfUpdateSlot(slot);
        }

        bookingRepository.saveAll(pendingBookings);

        List<Slot> slots = slotService.getSlotsByDate(todaysDate);
        for(Slot slot: slots) {
            if(slot.getCount() == 0) {continue;}
            Date nextDate = addDays(todaysDate, 1);
            List<Slot> nextSlot = slotService.getSlotByLocationAndDate(slot.getLocation(), nextDate);

            if(nextSlot.size() == 0) {
                Slot newSlot = Slot.builder()
                        .location(slot.getLocation())
                        .vaccine(slot.getVaccine())
                        .date(nextDate)
                        .count(slot.getCount())
                        .ageLimit(slot.getAgeLimit())
                        .build();
                slotService.createOfUpdateSlot(newSlot);
                slot.setCount(0);
                slotService.createOfUpdateSlot(slot);
            } else if (nextSlot.size() != 1) {
                throw new IllegalArgumentException("Illegal State having next Slots size="+ nextSlot.size());
            } else {
                Slot nextDaySlot = nextSlot.get(0);
                if(nextDaySlot.getVaccine() == slot.getVaccine()) {
                    nextDaySlot.setCount(nextDaySlot.getCount() + slot.getCount());
                    slotService.createOfUpdateSlot(nextDaySlot);
                    slot.setCount(0);
                    slotService.createOfUpdateSlot(slot);
                }
            }
        }
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return new Date(calendar.getTimeInMillis());
    }

    private boolean ageIsCorrect(User user, Slot slot) {
        if(user.getAge() >= slot.getAgeLimit().getMinAge()
                && (slot.getAgeLimit().getMaxAge() == null || user.getAge() < slot.getAgeLimit().getMaxAge())) {
            return true;
        }
        return false;
    }

    private boolean checkIfSlotIsOld(Slot slot) {
        LocalDate todaysDate = LocalDate.now();

        if (slot.getDate().toLocalDate().compareTo(todaysDate) < 0) {
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
//        Date date = Date.valueOf("2021-07-24");
//        Date nextDate = addDays(date, 1);
//        System.out.println(nextDate.toString());

    }
}
