package ru.student.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.student.db.mapper.AppointmentsMapper;
import ru.student.services.dto.AppointmentDto;
import ru.student.services.mapstruct.AppointmentStruct;
import ru.student.services.service.AppointmentsService;

import java.sql.Date;
import java.util.List;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final AppointmentsMapper appointmentsMapper;
    private final AppointmentStruct appointmentStruct;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public AppointmentsServiceImpl(AppointmentsMapper appointmentsMapper,
                                   AppointmentStruct appointmentStruct) {
        this.appointmentsMapper = appointmentsMapper;
        this.appointmentStruct = appointmentStruct;
    }

    @Override
    public List<AppointmentDto> getAppointments() {
        return appointmentStruct.toDto(appointmentsMapper.getAppointments());
    }

    @Override
    public List<AppointmentDto> getAppointmentsWithDate(Date date) {
        return appointmentStruct.toDto(appointmentsMapper.getAppointmentsWithDate(date));
    }
}
