package de.kreth.clubhelper.model.dao;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.model.data.Startpass;

public interface StartpassDao extends CrudRepository<Startpass, Long>, ClubhelperDao<Startpass> {
}
