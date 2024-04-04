package com.blood.rescue.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BloodRequest{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bloodGroup;
}