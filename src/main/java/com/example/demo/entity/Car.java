package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue
    private Integer id;

    private String make;

    private String model;

    private int seats;

    private double dailyPrice;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
