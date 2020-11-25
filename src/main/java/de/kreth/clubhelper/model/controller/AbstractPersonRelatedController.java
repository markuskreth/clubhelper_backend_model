package de.kreth.clubhelper.model.controller;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.BaseEntity;
import de.kreth.clubhelper.model.data.Person;
import de.kreth.clubhelper.model.data.PersonRelated;

public abstract class AbstractPersonRelatedController<T extends BaseEntity & PersonRelated, D extends CrudRepository<T, Long>>
	extends AbstractController<T, D> {

    private PersonDao personDao;

    protected AbstractPersonRelatedController(Class<T> element) {
	super(element);
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
	this.personDao = personDao;
    }

    @PutMapping(value = "/for/{id}")
    public void putForPerson(@PathVariable("id") long id, @RequestBody T toUpdate) {

	Optional<Person> person = personDao.findById(id);
	if (person.isEmpty()) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find person with id=" + id);
	}
	toUpdate.setPerson(person.get());
	toUpdate.setChanged(LocalDateTime.now());

	dao.save(toUpdate);
    }

    @PostMapping(value = "/for/{id}")
    public T postForPerson(@PathVariable("id") long id, @RequestBody T toCreate) {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime created = toCreate.getCreated();
	LocalDateTime changed = null;

	Optional<Person> person = personDao.findById(id);
	if (person.isEmpty()) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find person with id=" + id);
	}
	toCreate.setPerson(person.get());

	if (toCreate.getChanged() != null) {
	    changed = toCreate.getChanged();
	    long minutes = MINUTES.between(created, changed);
	    if (minutes < 1) {
		toCreate.setChanged(now);
	    }
	} else {
	    toCreate.setChanged(now);
	}

	return dao.save(toCreate);
    }
}
