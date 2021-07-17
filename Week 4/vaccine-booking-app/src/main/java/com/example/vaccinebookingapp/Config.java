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

import java.sql.Date;

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
                    .vaccine(Vaccine.COVAXIN)
                    .ageLimit(AgeLimit.AGE45toAbove)
                    .count(30)
                    .date(Date.valueOf("2021-07-18"))
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

            userRepository.save(User.builder()
                    .name("Pikachu1")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1998-07-11"))
                    .governmentId("PAN CARD: adkfjdf1")
                    .phoneNumber("1122334455")
                    .build()
            );

            userRepository.save(User.builder()
                    .name("Pikachu2")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1998-07-11"))
                    .governmentId("PAN CARD: adkfjdf2")
                    .phoneNumber("1122334456")
                    .build()
            );

            userRepository.save(User.builder()
                    .name("Pikachu3")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1998-07-11"))
                    .governmentId("PAN CARD: adkfjdf3")
                    .phoneNumber("1122334457")
                    .build()
            );

            userRepository.save(User.builder()
                    .name("Pikachu4")
                    .address("Palet Town")
                    .dateOfBirth(Date.valueOf("1998-07-11"))
                    .governmentId("PAN CARD: adkfjdf4")
                    .phoneNumber("1122334458")
                    .build()
            );

        };
    }
}
