package de.kreth.clubhelperbackend.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.dao.AttendanceDao;
import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Attendance;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController extends AbstractController<Attendance> {

	@Autowired
	private PersonDao personDao;
	
	public AttendanceController() {
		super(Attendance.class);
	}

   @RequestMapping(value = "/on", method = RequestMethod.POST, produces = "application/json")
   
   public List<Attendance> postListAttendencesOn(@RequestBody(required=false) Date date)
         throws SQLException {

      if(date == null) {
         date = new Date();
      }
      AttendanceDao tmpDao = (AttendanceDao)dao;
      
      return tmpDao.findByOnDate(date);
   }

   @RequestMapping(value = "/on/{date}", method = RequestMethod.GET, produces = "application/json")
   
   public List<Attendance> getAttendencesOn(@PathVariable("date") @DateTimeFormat(iso=ISO.DATE) Date date)
         throws SQLException {

      AttendanceDao tmpDao = (AttendanceDao)dao;
      
      return tmpDao.findByOnDate(date);
   }

	@RequestMapping(value = "/for/{id}", method = RequestMethod.POST, produces = "application/json")
	
	public Attendance post(@PathVariable("id") Long id) {
		Attendance att = new Attendance();
		att.setPerson(personDao.findById(id).get());
		att.setOnDate(new Date());
		return post(att);
	}
}
