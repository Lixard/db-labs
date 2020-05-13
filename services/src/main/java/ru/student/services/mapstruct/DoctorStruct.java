package ru.student.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.student.db.model.Doctor;
import ru.student.services.dto.DoctorDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorStruct {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "secondName", source = "secondName")
    DoctorDto toDto(Doctor doctor);

    Doctor fromDto(DoctorDto doctorDto);

    List<DoctorDto> toDto(List<Doctor> doctor);

    List<Doctor> fromDto(List<DoctorDto> doctorDto);
}
