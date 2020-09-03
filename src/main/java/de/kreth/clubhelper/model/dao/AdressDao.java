package de.kreth.clubhelper.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.data.Person;

public interface AdressDao extends CrudRepository<Adress, Integer> {

	List<Adress> findByPerson(Person person);

}
