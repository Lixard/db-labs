package ru.student.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.student.db.mapper.DoctorsMapper;
import ru.student.services.dto.DoctorDto;
import ru.student.services.mapstruct.DoctorStruct;
import ru.student.services.service.DoctorsService;

import java.util.List;

@Service
public class DoctorsServiceImpl implements DoctorsService {

    private final DoctorsMapper doctorsMapper;
    private final DoctorStruct doctorStruct;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public DoctorsServiceImpl(DoctorsMapper doctorsMapper,
                              DoctorStruct doctorStruct) {
        this.doctorsMapper = doctorsMapper;
        this.doctorStruct = doctorStruct;
    }


    @Override
    public List<DoctorDto> getDoctors() {
        return doctorStruct.toDto(doctorsMapper.getDoctors());
    }

    @Override
    public DoctorDto getById(int id) {
        return doctorStruct.toDto(doctorsMapper.getById(id));
    }

    @Override
    public void update(DoctorDto doctorDto) {
        doctorsMapper.update(doctorStruct.fromDto(doctorDto));
    }

    @Override
    public void insert(DoctorDto doctorDto) {
        doctorsMapper.insert(doctorStruct.fromDto(doctorDto));
    }

    @Override
    public void delete(int id) {
        doctorsMapper.delete(id);
    }
}
