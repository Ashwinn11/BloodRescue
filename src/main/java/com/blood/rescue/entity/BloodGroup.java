package com.blood.rescue.entity;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter

public enum BloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String value;

    BloodGroup(String value) {
        this.value = value;
    }

    public static BloodGroup valueToEnum(String text) {
        for (BloodGroup group : BloodGroup.values()) {
            if (group.value.equalsIgnoreCase(text)) {
                return group;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
