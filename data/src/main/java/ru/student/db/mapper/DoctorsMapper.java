package ru.student.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.student.db.model.Doctor;

import java.util.List;

@Mapper
public interface DoctorsMapper {

    @Select(
            //language=PostgreSQL
            "SELECT doctor_id, last_name, first_name, second_name FROM doctors"
    )
    List<Doctor> getDoctors();

    @Select(
            //language=PostgreSQL
            "SELECT doctor_id, last_name, first_name, second_name FROM doctors " +
            "WHERE doctor_id = #{doctorId}"
    )
    Doctor getById(@Param("id") int id);

    @Update(
            //language=PostgreSQL
            "UPDATE doctors " +
            "SET last_name = #{lastName}, " +
            "first_name = #{firstName}, " +
            "second_name = #{secondName} " +
            "WHERE doctor_id = #{doctorId}"
    )
    void update(Doctor doctor);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO doctors(last_name, first_name, second_name) VALUES(#{lastName}, #{firstName}, #{secondName})"
    )
    void insert(Doctor doctor);

    @Delete(
            //language=PostgreSQL
            "DELETE FROM doctors WHERE doctor_id = #{id}"
    )
    void delete(@Param("id") int id);
}
