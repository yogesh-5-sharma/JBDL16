package com.example.vaccinebookingapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private Integer bookingId;

    @Column(unique = true, nullable = false)
    private String referenceId;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Slot slot;

    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;
}
