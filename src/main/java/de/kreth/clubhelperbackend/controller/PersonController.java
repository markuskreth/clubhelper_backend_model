package de.kreth.clubhelperbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.data.Contact;
import de.kreth.clubhelper.model.data.Person;
import de.kreth.clubhelper.model.data.Relative;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;
import de.kreth.clubhelperbackend.controller.abstr.ClubController;

@RestController
@RequestMapping("/person")
public class PersonController extends AbstractController<Person> {

   @Autowired
	private ClubController<Contact> contactController;
   @Autowired
	private ClubController<Relative> relativeController;
   @Autowired
	private ClubController<Adress> adressController;

	public PersonController() {
		super(Person.class);
	}

//	private static ClubhelperDao<Person> doCast(PersonDao dao) {
//	   Object anonymous = dao;
//	   return (ClubhelperDao<Person>) anonymous;
//	}
	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Person> delete(@PathVariable("id") final long id) {

		for (Contact c : contactController.getByParentId(id)) {
			contactController.delete(c.getId());
		}
		for (Adress a : adressController.getByParentId(id)) {
			adressController.delete(a.getId());
		}
		for (Relative r : relativeController.getByParentId(id)) {
			relativeController.delete(r.getId());
		}

		return super.delete(id);
	}

	/**
	 * Delivers list with one Person with id.
	 */
	@Override
	public List<Person> getByParentId(long id) {
		List<Person> all = new ArrayList<Person>();
		all.add(getById(id));
		return all;
	}

	/**
	 * 
	 * @author markus
	 *
	 */
	public class PersonRelative extends Relative {

		private static final long serialVersionUID = 4828690343464403867L;
		private Person toPerson;
		private String relation;

		public PersonRelative(Relative r) {
			super(r.getId(),
					r.getToPerson2Relation(), r.getToPerson1Relation(), r.getPerson1Bean(), r.getPerson2Bean());
			setChanged(r.getChanged());
			setCreated(r.getCreated());
			toPerson = r.getPerson1Bean();
			relation = r.getToPerson1Relation();
		}

		public Person getToPerson() {
			return toPerson;
		}

		public String getRelation() {
			return relation;
		}

		@Override
		public String toString() {
			StringBuilder bld = new StringBuilder();
			bld.append(relation).append(" ").append(toPerson.getId())
					.append(": ").append(toPerson.getPrename()).append(" ")
					.append(toPerson.getSurname());
			return bld.toString();
		}
	}
}
