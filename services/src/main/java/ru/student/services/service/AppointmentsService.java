package ru.student.services.service;

import ru.student.services.dto.AppointmentDto;

import java.sql.Date;
import java.util.List;

public interface AppointmentsService {
    List<AppointmentDto> getAppointments();
    List<AppointmentDto> getAppointmentsWithDate(Date date);
}
