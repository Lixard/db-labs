package ru.student.services.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class DoctorDto {
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String secondName;
}
