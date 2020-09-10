package de.kreth.clubhelper.model.controller;

import java.util.Date;
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
public class AttendanceController
{

   @Autowired
   private AttendanceDao attendanceDao;

   @Autowired
   private PersonDao personDao;

   @GetMapping(value = "/on")
   @ResponseBody
   public List<Attendance> getAttendencesOn(@RequestBody @DateTimeFormat(iso=ISO.DATE) Date date)
   {
      if (date == null) {
         date = new Date();
      }
      return attendanceDao.findByOnDate(date);
   }

   @PostMapping(value = "/for/{id}")
   @ResponseBody
   public Attendance post(@PathVariable("id") Long id)
   {
      Attendance att = new Attendance();
      att.setOnDate(new Date());
      att.setPerson(personDao.findById(id).orElseThrow(() -> new RuntimeException("Person not found by id=" + id)));
      attendanceDao.save(att);
      return att;
   }
   
   @DeleteMapping("/{id}")
   public Attendance delete(@PathVariable("id") Long personId, @RequestBody(required = true) Date onDate) {
      Person person = personDao.findById(personId).orElseThrow(() -> new RuntimeException("Person not found by id=" + personId));
      Attendance attendance = attendanceDao.findByPersonAndOnDate(person, onDate);
      attendanceDao.delete(attendance);
      return attendance;
   }
}
