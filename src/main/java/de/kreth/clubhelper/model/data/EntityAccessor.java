package de.kreth.clubhelper.model.data;


import java.util.Date;

public interface EntityAccessor {

	Object getId();

	boolean hasValidId();

	void setChanged(Date changed);

	void setCreated(Date created);

}
