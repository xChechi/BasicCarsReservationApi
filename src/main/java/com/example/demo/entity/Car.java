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
@Table(name = "cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"make", "model", "seats"})
})
public class Car {

    @Id
    @GeneratedValue
    private Integer id;

    private String make;

    private String model;

    private int seats;

    private double dailyCharge;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
