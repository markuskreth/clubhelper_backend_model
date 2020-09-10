package de.kreth.clubhelper.model.dao;

import java.util.Date;
import java.util.List;

import de.kreth.clubhelper.model.data.Attendance;
import de.kreth.clubhelper.model.data.Person;

public interface AttendanceDao extends ClubhelperDao<Attendance> {

	List<Attendance> findByOnDate(Date onDate);

	List<Attendance> findByPerson(Person person);

	Attendance findByPersonAndOnDate(Person person, Date onDate);

}
