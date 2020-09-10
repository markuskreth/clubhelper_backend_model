package de.kreth.clubhelper.model.dao;

import java.util.List;

import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.data.Person;

public interface AdressDao extends ClubhelperDao<Adress> {

	List<Adress> findByPerson(Person person);

}
