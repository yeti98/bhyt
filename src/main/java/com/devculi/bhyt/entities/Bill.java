package com.devculi.bhyt.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Category category;
    @Column(updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;
    @Column(updatable = false, nullable = false)
    private double amount;
    @OneToOne
    private User user;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String personalID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(updatable = false, nullable = false)
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(updatable = false, nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private String firstRegistrationPlace;
    @Column(updatable = false, unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private double baseSalary;
}

