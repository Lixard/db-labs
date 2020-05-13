package ru.student.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.student.db.model.Doctor;
import ru.student.services.dto.DoctorDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorStruct {

    @Mapping(target = "id", source = "doctorId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "secondName", source = "secondName")
    DoctorDto toDto(Doctor doctor);

    @Mapping(target = "doctorId", source = "id")
    Doctor fromDto(DoctorDto doctorDto);

    List<DoctorDto> toDto(List<Doctor> doctor);

    List<Doctor> fromDto(List<DoctorDto> doctorDto);
}
