package ru.student.db.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private String secondName;
}
