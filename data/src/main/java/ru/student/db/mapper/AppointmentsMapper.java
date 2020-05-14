package ru.student.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.student.db.model.Appointment;

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
            "GROUP BY p.last_name"
    )
//    @Results({
//            @Result(column = "id", property = "appointmentId"),
//            @Result(column = "", property = "patientLastName"),
//            @Result(column = "d.last_name", property = ""),
//            @Result(column = "", property = ""),
//            @Result(column = "", property = "")
//    })
    List<Appointment> getAppointments();
}
