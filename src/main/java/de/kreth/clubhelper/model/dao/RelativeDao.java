package de.kreth.clubhelper.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.model.data.Relative;

public interface RelativeDao extends CrudRepository<Relative, Long> {

    List<Relative> findByPerson1ORPerson2(long personId);

}
