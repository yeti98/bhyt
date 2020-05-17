package com.devculi.bhyt.dto;

import com.devculi.bhyt.entities.Category;
import com.devculi.bhyt.entities.User;
import com.devculi.bhyt.enums.CalculatorType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private LocalDateTime timeStamp;
    private double amount;
    private CalculatorType type;
    private User user;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;
    private String firstRegistrationPlace;
    private String code;
    private double baseSalary;
    private Category category;
    private String familyCode;
}
