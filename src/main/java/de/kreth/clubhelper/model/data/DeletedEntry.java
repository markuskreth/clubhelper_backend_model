package de.kreth.clubhelper.model.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the deleted_entries database table.
 * 
 */
@Entity
@Table(name = "deleted_entries")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "DeletedEntry.findAll", query = "SELECT d FROM DeletedEntry d")
public class DeletedEntry extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4271189592258131845L;

    private int entryId;

    private String tablename;

    public int getEntryId() {
	return entryId;
    }

    public void setEntryId(int entryId) {
	this.entryId = entryId;
    }

    public String getTablename() {
	return tablename;
    }

    public void setTablename(String tablename) {
	this.tablename = tablename;
    }

    @Override
    public int hashCode() {
	final int prime = 47;
	int result = super.hashCode();
	result = prime * result + entryId;
	result = prime * result + ((tablename == null) ? 0 : tablename.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	return super.equals(obj);
    }

    @Override
    public String toString() {
	return "DeletedEntry [entryId=" + entryId + ", tablename=" + tablename + "]";
    }

}
