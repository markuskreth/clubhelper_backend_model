package de.kreth.clubhelper.model.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.kreth.clubhelper.model.dao.ContactDao;
import de.kreth.clubhelper.model.data.Contact;
import de.kreth.clubhelper.model.data.Person;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactDao contactDao;

	public List<Contact> getByParent(Person person) {
		return contactDao.findByPerson(person);
	}

	public void delete(Contact c) {
		c.setDeleted(new Date());
		contactDao.save(c);
	}

}
