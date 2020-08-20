package de.kreth.clubhelper.model.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonDao personDao;
	@Autowired
	private ContactController contactController;
	@Autowired
	private AdressController adressController;

	@GetMapping
	public @ResponseBody Iterable<Person> getAll() {
		return personDao.findAll();
	}

	@GetMapping(value = "/{id}")
	public @ResponseBody Optional<Person> getById(@PathVariable("id") final int id) {
		return personDao.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody Person delete(@PathVariable("id") final int id) {

		Optional<Person> optional = personDao.findById(id);
		if (optional.isPresent()) {
			Person person = optional.get();
			for (Contact c : contactController.getByParent(person)) {
				contactController.delete(c);
			}

			for (Adress a : adressController.getByParent(person)) {
				adressController.delete(a);
			}
			person.setDeleted(new Date());
			personDao.save(person);
		}
		return optional.orElseThrow();
	}

//	/**
//	 * 
//	 * @author markus
//	 *
//	 */
//	public class PersonRelative extends Relative {
//
//		private static final long serialVersionUID = 4828690343464403867L;
//		private Person toPerson;
//		private String relation;
//
//		public PersonRelative(Relative r) {
//			super(r.getId(), r.getPerson1(), r.getPerson2(), r.getToPerson2Relation(), r.getToPerson1Relation(),
//					r.getChanged(), r.getCreated());
//			toPerson = getById(r.getPerson1());
//			relation = r.getToPerson1Relation();
//		}
//
//		public Person getToPerson() {
//			return toPerson;
//		}
//
//		public String getRelation() {
//			return relation;
//		}
//
//		@Override
//		public String toString() {
//			StringBuilder bld = new StringBuilder();
//			bld.append(relation).append(" ").append(toPerson.getId()).append(": ").append(toPerson.getPrename())
//					.append(" ").append(toPerson.getSurname());
//			return bld.toString();
//		}
//	}
}
