package de.kreth.clubhelper.model.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c WHERE c.deleted is not null")
public class Contact extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7631864028095077913L;

    public static enum Type {
	PHONE("Telefon"),
	MOBILE("Mobile"),
	EMAIL("Email");

	private final String name;

	private Type(String name) {
	    this.name = name;
	}

	public String getName() {
	    return name;
	}
    }

    private String type;

    private String value;

    // bi-directional many-to-one association to Person
    @ManyToOne
    private Person person;

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    @Override
    public int hashCode() {
	final int prime = 37;
	int result = super.hashCode();
	result = prime * result;
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
	return "Contact [type=" + type + ", value=" + value + "]";
    }

}
