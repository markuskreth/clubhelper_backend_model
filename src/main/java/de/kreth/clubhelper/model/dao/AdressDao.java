package de.kreth.clubhelper.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.entity.Adress;
import de.kreth.clubhelper.entity.Person;

public interface AdressDao extends CrudRepository<Adress, Long>, ClubhelperDao<Adress> {

    List<Adress> findByPerson(Person person);

    @Override
    List<Adress> findByPersonId(long personId);

    @Override
    List<Adress> findByChangedGreaterThan(Date date);
}
