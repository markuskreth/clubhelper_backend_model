package de.kreth.clubhelper.model.dao;

import java.util.Date;
import java.util.List;

public interface ClubhelperDao<T>
{

   List<T> findByPersonId(long personId);

   List<T> findByChangedGreaterThan(Date date);
}
