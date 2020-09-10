package de.kreth.clubhelper.model.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.model.data.Attendance;
import de.kreth.clubhelper.model.data.Person;

public interface AttendanceDao extends CrudRepository<Attendance, Long> {

    List<Attendance> findByOnDate(LocalDate onDate);

    List<Attendance> findByPerson(Person person);

    Attendance findByPersonAndOnDate(Person person, Date onDate);

    List<Attendance> findByPersonId(long personId);

    List<Attendance> findByChangedGreaterThan(LocalDateTime date);
}
