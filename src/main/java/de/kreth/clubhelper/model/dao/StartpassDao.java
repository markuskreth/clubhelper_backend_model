package de.kreth.clubhelper.model.dao;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.data.Startpass;

public interface StartpassDao extends CrudRepository<Startpass, Long>, ClubhelperDao<Startpass> {
}
