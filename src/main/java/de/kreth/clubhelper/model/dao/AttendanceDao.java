package de.kreth.clubhelper.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.model.data.Attendance;
import de.kreth.clubhelper.model.data.Person;

public interface AttendanceDao extends CrudRepository<Attendance, Integer> {

	List<Attendance> findByOnDate(Date onDate);

	List<Attendance> findByPerson(Person person);

}
