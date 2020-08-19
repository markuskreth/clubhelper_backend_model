package de.kreth.clubhelper.model.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kreth.clubhelper.model.dao.AttendanceDao;
import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Attendance;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	private AttendanceDao attendanceDao;
	private PersonDao personDao;

	@Autowired
	public AttendanceController(AttendanceDao attendanceDao, PersonDao personDao) {
		this.attendanceDao = attendanceDao;
		this.personDao = personDao;

	}

	@PostMapping(value = "/on")
	@ResponseBody
	public List<Attendance> getAttendencesOn(@RequestBody(required = false) Date date) {

		if (date == null) {
			date = new Date();
		}

		return attendanceDao.findByOnDate(date);
	}

	@PostMapping(value = "/for/{id}")
	@ResponseBody
	public Attendance post(@PathVariable("id") Integer id) {

		Attendance att = new Attendance();
		att.setOnDate(new Date());
		att.setPerson(personDao.findById(id).orElseThrow());
		attendanceDao.save(att);
		return att;
	}
}
