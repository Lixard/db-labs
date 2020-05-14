package ru.student.services.service;

import ru.student.services.dto.AppointmentDto;

import java.util.List;

public interface AppointmentsService {
    List<AppointmentDto> getAppointments();
}
