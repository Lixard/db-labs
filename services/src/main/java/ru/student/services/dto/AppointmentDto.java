package ru.student.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AppointmentDto {
    private int appointmentId;
    private String patientLastName;
    private String doctorLastName;
    private String appointmentPlace;
    private Timestamp appointmentDate;
}
