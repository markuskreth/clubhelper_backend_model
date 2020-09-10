package de.kreth.clubhelper.model.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.dao.ContactDao;
import de.kreth.clubhelper.model.data.Contact;
import de.kreth.clubhelper.model.data.Person;

@RestController
@RequestMapping("/contact")
@PreAuthorize("isAuthenticated()")
public class ContactController
{
   @Autowired
   private ContactDao contactDao;

   public List<Contact> getByParent(Person person)
   {
      return contactDao.findByPerson(person);
   }

   public void delete(Contact c)
   {
      c.setDeleted(new Date());
      contactDao.save(c);
   }
}
