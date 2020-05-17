package com.devculi.bhyt.entities;

import com.devculi.bhyt.enums.CalculatorType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    private LocalDateTime timeStamp;
    private double amount;
    @Enumerated(EnumType.STRING)
    private CalculatorType type;
    @OneToOne
    private User user;
    private String name;
    @NotNull
    @Column(updatable = false, unique = true)
    private String personalID;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @NotNull
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(updatable = false)
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(updatable = false)
    private LocalDate expiryDate;
    private String firstRegistrationPlace;
    @NotNull
    @Column(updatable = false, unique = true)
    @Size(min = 1, max = 20, message = "Mã có độ dài từ 1 đến 50 ký tự")
    private String code;
    private double baseSalary;
}

