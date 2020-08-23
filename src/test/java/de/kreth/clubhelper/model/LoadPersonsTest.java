package de.kreth.clubhelper.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Gender;
import de.kreth.clubhelper.model.data.Person;

@DataJpaTest()
@Sql(scripts = "classpath:data.sql")
class LoadPersonsTest {

    @Autowired
    PersonDao personDao;

    @Test
    void testLoadPerson1() {

	Optional<Person> person1 = personDao.findById(1);
	assertTrue(person1.isPresent(), "Person with id=1 not found!");

    }

    @Test
    void testLoadAll() {
	Iterable<Person> all = personDao.findAll();
	assertTrue(all.iterator().hasNext(), "No data found in Person");
    }

    @Test
    void insertPerson() {
	Person p = new Person();
	p.setPrename("prename");
	p.setSurname("surname");
	p.setBirth(LocalDate.of(1981, 3, 3));
	p.setGender(Gender.MALE);

	personDao.save(p);

	assertNotNull(p.getId());
	personDao.delete(p);
    }

}
