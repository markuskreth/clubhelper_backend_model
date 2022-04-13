package de.kreth.clubhelper.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.entity.Contact;
import de.kreth.clubhelper.entity.Person;

public interface ContactDao extends CrudRepository<Contact, Long>, ClubhelperDao<Contact> {

    List<Contact> findByPerson(Person person);

    @Override
    List<Contact> findByPersonId(long personId);

}
