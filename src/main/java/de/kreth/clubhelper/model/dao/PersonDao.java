package de.kreth.clubhelper.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.kreth.clubhelper.data.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {

    List<Person> findByChangedGreaterThan(Date date);

    List<Person> findByDeletedIsNull();

    @Override
    List<Person> findAll();

}
