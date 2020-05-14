package ru.student.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.student.db.model.Appointment;

import java.sql.Date;
import java.util.List;

@Mapper
public interface AppointmentsMapper {

    @Select(
            //language=PostgreSQL
            "SELECT a.appointment_id as id, " +
            "p.last_name as patient_last_name, " +
            "d.last_name as doctor_last_name, " +
            "a.place as appointment_place, " +
            "a.appointment_date as appointment_date " +
            "FROM appointments a " +
            "JOIN patients p on a.patient_id = p.patient_id " +
            "JOIN doctors d on a.doctor_id = d.doctor_id " +
            "GROUP BY a.appointment_id, p.last_name, d.last_name, a.place, a.appointment_date"
    )
    List<Appointment> getAppointments();

    @Select(
            //language=PostgreSQL
            "SELECT a.appointment_id as id, " +
            "p.last_name as patient_last_name, " +
            "d.last_name as doctor_last_name, " +
            "a.place as appointment_place, " +
            "a.appointment_date as appointment_date " +
            "FROM appointments a " +
            "JOIN patients p on a.patient_id = p.patient_id " +
            "JOIN doctors d on a.doctor_id = d.doctor_id " +
            "GROUP BY a.appointment_id, p.last_name, d.last_name, a.place, a.appointment_date " +
            "HAVING appointment_date::date = #{date}"
    )
    List<Appointment> getAppointmentsWithDate(@Param("date") Date date);
}
