package com.devculi.bhyt.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(updatable = false, unique = true)
    @Size(min = 1, max = 20, message = "Mã có độ dài từ 1 đến 50 ký tự")
    private String code;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private double value;
    private String valueDescription;
    private double minValue;
}
