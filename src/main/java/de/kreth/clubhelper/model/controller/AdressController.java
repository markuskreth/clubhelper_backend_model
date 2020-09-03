package de.kreth.clubhelper.model.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.kreth.clubhelper.model.dao.AdressDao;
import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.data.Person;

@Controller
@RequestMapping("/adress")
@PreAuthorize("isAuthenticated()")
public class AdressController {

    @Autowired
    private AdressDao adressDao;

    public List<Adress> getByParent(Person person) {
	return adressDao.findByPerson(person);
    }

    public void delete(Adress a) {
	a.setDeleted(new Date());
	adressDao.save(a);
    }

}
