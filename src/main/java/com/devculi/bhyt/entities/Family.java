package com.devculi.bhyt.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true, nullable = false)
    @Size(min = 1, max = 20, message = "Mã có độ dài từ 1 đến 50 ký tự")
    private String code;
    @OneToMany
    private List<Bill> bills = new ArrayList<>();
}
