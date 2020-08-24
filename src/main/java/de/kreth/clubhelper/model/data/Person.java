package de.kreth.clubhelper.model.data;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = Person.QUERY_FINDALL, query = "SELECT p FROM Person p WHERE p.deleted is null")
@NamedQuery(name = Person.QUERY_FINDLOGIN, query = "FROM Person WHERE username = :username AND password = :password AND deleted is"
	+ " null")
public class Person extends BaseEntity implements Serializable {

    public static final String SESSION_LOGIN = "SESSION_LOGIN_USER";

    public static final String QUERY_FINDALL = "Person.findAll";

    public static final String QUERY_FINDLOGIN = "Person.findLogin";

    private static final long serialVersionUID = -8361264400619997123L;

    @Basic
    private LocalDate birth;

    private String prename;

    private String surname;

    private String username;

    private String password;

    private Integer gender;

    public Gender getGender() {
	if (gender == null) {
	    return null;
	}
	return Gender.valueOf(gender);
    }

    public void setGender(Gender gender) {
	if (gender == null) {
	    this.gender = null;
	} else {
	    this.gender = gender.getId();
	}
    }

    public LocalDate getBirth() {
	return birth;
    }

    public void setBirth(LocalDate birth) {
	this.birth = birth;
    }

    public String getPrename() {
	return prename;
    }

    public void setPrename(String prename) {
	this.prename = prename;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setGender(Integer gender) {
	this.gender = gender;
    }

    @Override
    public String toString() {
	return "Person [id=" + getId() + ", prename=" + prename + ", surname=" + surname + "]";
    }

}
