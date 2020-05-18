package com.devculi.bhyt.entities;

import com.devculi.bhyt.enums.CalculatorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true, nullable = false)
    private String code;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CalculatorType type;
    private String valueDescription;
    @Column(nullable = true)
    private Double minSupportedPercent;
    @Column(nullable = true)
    private Double defaultBaseSalary;
}
