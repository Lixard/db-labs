package ru.student.services.service;

import ru.student.services.dto.DoctorDto;

import java.util.List;

public interface DoctorsService {
    List<DoctorDto> getDoctors();
    DoctorDto getById(int id);
    void update(DoctorDto doctorDto);
    void insert(DoctorDto doctorDto);
    void delete(int id);
}
