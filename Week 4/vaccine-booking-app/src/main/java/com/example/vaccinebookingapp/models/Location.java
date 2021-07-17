package com.example.vaccinebookingapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "locations")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "locationId"
)
public class Location {

    @Id
    @GeneratedValue
    private Integer locationId;

    @NotNull
    private String centreName;

    @NotNull
    private String address;

    @NotNull
    @Min(100000)
    @Max(999999)
    private Integer pinCode;

    @Enumerated(EnumType.STRING)
    private Agency agency;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Slot> slots;
}
