package de.kreth.clubhelper.model.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.kreth.clubhelper.model.dao.AdressDao;
import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.data.Person;

@Controller
@RequestMapping("/adress")
public class AdressController {

	private AdressDao adressDao;

	@Autowired
	public AdressController(AdressDao adressDao) {
		this.adressDao = adressDao;
	}

	public List<Adress> getByParent(Person person) {
		return adressDao.findByPerson(person);
	}

	public void delete(Adress a) {
		a.setDeleted(new Date());
		adressDao.save(a);
	}

}
