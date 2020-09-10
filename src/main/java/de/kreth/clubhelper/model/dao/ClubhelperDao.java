package de.kreth.clubhelper.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ClubhelperDao<T> extends CrudRepository<T, Long>
{
   List<T> findByPersonId(long personId);

   List<T> findByChangedGreaterThan(Date date);
}
