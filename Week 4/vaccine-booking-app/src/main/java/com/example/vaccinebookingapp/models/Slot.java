package com.example.vaccinebookingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@ToString
@Table(name = "slots")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "slotId"
)
public class Slot {

    @Version
    private Integer version;

    @Id
    @GeneratedValue
    private Integer slotId;

    // first thing refers to class, second thing refers to other class
    @NotNull
    @ManyToOne
    @JoinColumn(name = "location_id")
//    @JsonBackReference
    private Location location;

    @NotNull
    private Date date;

    @Enumerated(EnumType.STRING)
    private Vaccine vaccine;

    @NotNull
    @Min(0)
    private Integer count;

    @Enumerated(EnumType.STRING)
    private AgeLimit ageLimit;


}
