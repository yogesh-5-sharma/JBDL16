package com.example.vaccinebookingapp;

import com.example.vaccinebookingapp.models.*;
import com.example.vaccinebookingapp.repository.LocationRepository;
import com.example.vaccinebookingapp.repository.SlotRepository;
import com.example.vaccinebookingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Configuration
public class Config {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    UserRepository userRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            locationRepository.save(Location.builder()
                    .centreName("ABC")
                    .address("ABC somthing")
                    .agency(Agency.GOVERNMENT)
                    .pinCode(110011)
                    .build()
            );

            locationRepository.save(Location.builder()
                    .centreName("DEF")
                    .address("DEF somthing")
                    .agency(Agency.GOVERNMENT)
                    .pinCode(110012)
                    .build()
            );

            locationRepository.save(Location.builder()
                    .centreName("MNO")
                    .address("MNO somthing")
                    .agency(Agency.PRIVATE)
                    .pinCode(110013)
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(1))
                    .vaccine(Vaccine.COVISHIELD)
                    .ageLimit(AgeLimit.AGE0toAbove)
                    .count(40)
                    .date(Date.valueOf("2021-07-17"))
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(1))
                    .vaccine(Vaccine.COVISHIELD)
                    .ageLimit(AgeLimit.AGE45toAbove)
                    .count(30)
                    .date(Date.valueOf("2021-07-18"))
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(2))
                    .vaccine(Vaccine.COVISHIELD)
                    .ageLimit(AgeLimit.AGE18toAbove)
                    .count(30)
                    .date(Date.valueOf("2021-08-19"))
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(2))
                    .vaccine(Vaccine.COVISHIELD)
                    .ageLimit(AgeLimit.AGE18toAbove)
                    .count(30)
                    .date(Date.valueOf("2021-07-24"))
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(2))
                    .vaccine(Vaccine.COVISHIELD)
                    .ageLimit(AgeLimit.AGE18toAbove)
                    .count(30)
                    .date(Date.valueOf("2021-07-25"))
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(2))
                    .vaccine(Vaccine.SPUTNIKV)
                    .ageLimit(AgeLimit.AGE18toAbove)
                    .count(10)
                    .date(Date.valueOf("2021-07-18"))
                    .build()
            );

            slotRepository.save(Slot.builder()
                    .location(locationRepository.getById(3))
                    .vaccine(Vaccine.SPUTNIKV)
                    .ageLimit(AgeLimit.AGE18toAbove)
                    .count(1)
                    .date(Date.valueOf("2021-07-18"))
                    .build()
            );



            userRepository.save(User.builder()
                    .name("Pikachu1")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1950-07-11"))
                    .governmentId("PAN CARD: adkfjdf1")
                    .phoneNumber("1122334455")
                    .build()
            );

            userRepository.save(User.builder()
                    .name("Pikachu2")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1970-07-11"))
                    .governmentId("PAN CARD: adkfjdf2")
                    .phoneNumber("1122334456")
                    .build()
            );

            userRepository.save(User.builder()
                    .name("Pikachu3")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1990-07-11"))
                    .governmentId("PAN CARD: adkfjdf3")
                    .phoneNumber("1122334457")
                    .build()
            );

            userRepository.save(User.builder()
                    .name("Pikachu4")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("2010-07-11"))
                    .governmentId("PAN CARD: adkfjdf4")
                    .phoneNumber("1122334458")
                    .build()
            );

            List<Slot> slot = slotRepository.findByLocationAndDate(locationRepository.getById(3), Date.valueOf("2021-07-18"));

//            System.out.println(slot);
        };
    }
}
