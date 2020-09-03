package de.kreth.clubhelper.model.dao;

import org.springframework.data.repository.CrudRepository;

import de.kreth.clubhelper.model.data.GroupDef;

public interface GroupDao extends CrudRepository<GroupDef, Integer> {

}
