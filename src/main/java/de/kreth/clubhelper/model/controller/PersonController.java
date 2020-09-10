package de.kreth.clubhelper.model.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.data.Contact;
import de.kreth.clubhelper.model.data.Person;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/person")
@PreAuthorize("isAuthenticated()")
public class PersonController
{
   @Autowired
   private PersonDao personDao;
   
   @Autowired
   private ContactController contactController;
   
   @Autowired
   private AdressController adressController;

   @GetMapping
   @PreAuthorize("hasAnyRole('trainer', 'admin')")
   @ApiOperation("Get a list of all persons. Restricted to trainers and admins.")
   public @ResponseBody Iterable<Person> getAll()
   {
      return personDao.findAll();
   }

   @GetMapping(value = "/{id}")
   public @ResponseBody Optional<Person> getById(@PathVariable("id") final long id)
   {
      return personDao.findById(id);
   }

   @DeleteMapping(value = "/{id}")
   public @ResponseBody Person delete(@PathVariable("id") final long id)
   {
      Optional<Person> optional = personDao.findById(id);
      if (optional.isPresent()) {
         Person person = optional.get();
         for (Contact c : contactController.getByParent(person)) {
            contactController.delete(c);
         }
         for (Adress a : adressController.getByParent(person)) {
            adressController.delete(a);
         }
         person.setDeleted(LocalDateTime.now());
         personDao.save(person);
      }
      return optional.orElseThrow(() -> new RuntimeException("Person not found by id=" + id));
   }
}
