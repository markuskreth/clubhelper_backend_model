package de.kreth.clubhelper.model.data;


import java.time.LocalDateTime;

public interface EntityAccessor {

	Object getId();

	boolean hasValidId();

	void setChanged(LocalDateTime changed);

	void setCreated(LocalDateTime created);

}
