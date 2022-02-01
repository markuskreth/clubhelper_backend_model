package de.kreth.clubhelper.model.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kreth.clubhelper.model.config.LocalDateTimeProvider;
import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/person")
@PreAuthorize("isAuthenticated()")
@Api(tags = "Person")
public class PersonController {

	private LocalDateTimeProvider localDateTimeProvider;

	private PersonDao personDao;

	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Autowired
	public void setLocalDateTimeProvider(LocalDateTimeProvider localDateTimeProvider) {
		this.localDateTimeProvider = localDateTimeProvider;
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('trainer', 'admin')")
    @ApiOperation("Get a list of all persons. Restricted to trainers and admins.")
	public @ResponseBody List<Person> getAll() {
		return personDao.findByDeletedIsNull();
	}

	@GetMapping(value = "/withdeleted")
	@PreAuthorize("hasAnyRole('trainer', 'admin')")
    @ApiOperation("Get a list of all persons. Restricted to trainers and admins.")
	public @ResponseBody List<Person> getAllIncludingDeleted() {
		return personDao.findAll();
	}

	@GetMapping(value = "/{id}")
	public @ResponseBody Optional<Person> getById(@PathVariable("id") final long id) {
		return personDao.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody Person delete(@PathVariable("id") final long id) {
		Optional<Person> optional = personDao.findById(id);
		if (optional.isPresent()) {
			Person person = optional.get();
//	    for (Contact c : contactController.getByParentId(person.getId())) {
//		contactController.delete(c.getId());
//	    }
//	    for (Adress a : adressController.getByParentId(person.getId())) {
//		adressController.delete(a.getId());
//	    }
			person.setDeleted(localDateTimeProvider.now());
			personDao.save(person);
		}

		return optional.orElseThrow(() -> new RuntimeException("Person not found by id=" + id));
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('trainer', 'admin')")
//    @ApiOperation("Change an existing Person. Restricted to trainers and admins.")
	public Person update(@PathVariable("id") final long id, @RequestBody Person person) {
		if (id != person.getId()) {
			throw new IllegalArgumentException("path id must match person id.");
		}
		if (person.getId() == null || person.getId() < 0) {
			throw new IllegalStateException("For update id must be set and person must be persistent.");
		}
		person.setChanged(localDateTimeProvider.now());
		personDao.save(person);
		return person;
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('trainer', 'admin')")
//    @ApiOperation("Insert a new Person. Restricted to trainers and admins.")
	public Person insert(@RequestBody Person p) {

//	Person p = new Person();
//	p.setPrename(person.getPrename());
//	p.setSurname(person.getSurname());
//	p.setBirth(person.getBirth());
//	p.setGender(person.getGender());

		LocalDateTime now = localDateTimeProvider.now();
		p.setChanged(now);
		p.setCreated(now);
		return personDao.save(p);
	}

}
