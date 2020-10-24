package de.kreth.clubhelper.model.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kreth.clubhelper.model.dao.AttendanceDao;
import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Attendance;
import de.kreth.clubhelper.model.data.Person;

@Controller
@RequestMapping("/attendance")
@PreAuthorize("hasRole('trainer') or hasRole('admin')")
public class AttendanceController {

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private PersonDao personDao;

    @GetMapping(value = "/{date}")
    @ResponseBody
    public List<Attendance> getAttendencesOn(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
	List<Attendance> findByOnDate = attendanceDao.findByOnDate(date);
	return findByOnDate;
    }

    @PostMapping(value = "/for/{id}")
    @ResponseBody
    public Attendance post(@PathVariable("id") Long id, @RequestBody(required = false) LocalDate onDate) {
	Attendance att = new Attendance();
	if (onDate == null) {
	    att.setOnDate(LocalDate.now());
	} else {
	    att.setOnDate(onDate);
	}
	att.setPerson(personDao.findById(id).orElseThrow(() -> new RuntimeException("Person not found by id=" + id)));
	LocalDateTime now = LocalDateTime.now();
	att.setChanged(now);
	att.setCreated(now);
	attendanceDao.save(att);
	return att;
    }

    @DeleteMapping("/{id}/{onDate}")
    public void delete(@PathVariable("id") Long personId,
	    @PathVariable("onDate") @DateTimeFormat(iso = ISO.DATE) LocalDate onDate) {

	Person person = personDao.findById(personId)
		.orElseThrow(() -> new RuntimeException("Person not found by id=" + personId));
	Attendance attendance = attendanceDao.findByPersonAndOnDate(person, onDate);
	attendance.setDeleted(LocalDateTime.now());
	attendanceDao.delete(attendance);
    }
}
